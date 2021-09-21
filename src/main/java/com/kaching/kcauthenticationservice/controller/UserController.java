package com.kaching.kcauthenticationservice.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.kaching.kcauthenticationservice.client.FirebaseAccessToken;
import com.kaching.kcauthenticationservice.command.RegisterUserCommand;
import com.kaching.kcauthenticationservice.dto.LoginTokenDto;
import com.kaching.kcauthenticationservice.exception.LoginException;
import com.kaching.kcauthenticationservice.exception.UnhandledException;
import com.kaching.kcauthenticationservice.gateway.CommandGateway;
import com.kaching.kcauthenticationservice.request.LoginRequest;
import com.kaching.kcauthenticationservice.request.RegisterUserRequest;
import com.kaching.kcauthenticationservice.request.WebClientLoginRequest;
import com.kaching.libidentity.annotation.PublicApi;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@FieldDefaults(makeFinal = true)
@Slf4j(topic = "[UserController]")
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    private CommandGateway commandGateway;
    private WebClient webClient;

    @Autowired
    public UserController(CommandGateway commandGateway, @Qualifier("firebase-login-url") WebClient webClient) {
        this.commandGateway = commandGateway;
        this.webClient = webClient;
    }

    @PublicApi
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        var command = RegisterUserCommand.builder()
           .email(request.getEmail())
           .username(request.getUsername())
           .password(request.getPassword())
           .build();
        commandGateway.send(command);
        return responseOk();
    }

    @PublicApi
    @PostMapping("/login")
    public ResponseEntity<LoginTokenDto> login(@Valid @RequestBody LoginRequest request) {
        log.info("Request: [{}]", request.toString());

        var firebaseToken =
           webClient.post()
              .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
              .body(Mono.just(new WebClientLoginRequest(request.getEmail(), request.getPassword(), true)), WebClientLoginRequest.class)
              .retrieve()
              .onStatus(
                 HttpStatus::is4xxClientError,
                 clientResponse ->
                    clientResponse
                       .bodyToMono(JsonNode.class)
                       .map(error -> {
                           log.info("Log error: \n{}", error.toPrettyString());
                           return error;
                       })
                       .map(error -> error.get("error").get("message").textValue())
                       .flatMap(message -> Mono.error(new LoginException(message)))
              )
              .bodyToMono(FirebaseAccessToken.class)
              .block();

        if (Optional.ofNullable(firebaseToken).isPresent()) {
            return responseOk(new LoginTokenDto(firebaseToken.getLocalId(), firebaseToken.getIdToken(), firebaseToken.getRefreshToken(), firebaseToken.getExpiresIn()));
        } else {
            log.error("Firebase token is null");
            throw new UnhandledException("Firebase token is null.");
        }
    }
}
