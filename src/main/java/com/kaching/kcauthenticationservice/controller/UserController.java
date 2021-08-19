package com.kaching.kcauthenticationservice.controller;

import com.kaching.kcauthenticationservice.command.RegisterUserCommand;
import com.kaching.kcauthenticationservice.gateway.CommandGateway;
import com.kaching.kcauthenticationservice.request.RegisterUserRequest;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
@RequestMapping(value = "/api/user")
public class UserController extends BaseController {

    private CommandGateway commandGateway;

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
}
