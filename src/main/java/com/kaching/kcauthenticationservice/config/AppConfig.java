package com.kaching.kcauthenticationservice.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class AppConfig {

    @Value("${kaching.google.refreshToken.path}")
    private String googleRefreshTokenPath;

    @Value("${kaching.google.webClient.apiKey}")
    private String googleWebApiKey;

    @Value("${kaching.google.webClient.signInHost}")
    private String googleSignInHost;

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        FileInputStream refreshToken = new FileInputStream(googleRefreshTokenPath);

        FirebaseOptions options = FirebaseOptions.builder()
           .setCredentials(GoogleCredentials.fromStream(refreshToken))
           .build();

        return FirebaseApp.initializeApp(options);
    }

    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {
        return FirebaseAuth.getInstance(firebaseApp());
    }

    @RequestScope
    @Bean(name = "firebase-login-url")
    public WebClient firebaseWebClient() {
        return WebClient.builder()
           .baseUrl(String.format("%s?key=%s", googleSignInHost, googleWebApiKey))
           .defaultCookie("cookieKey", "cookieValue")
           .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
           .build();
    }
}
