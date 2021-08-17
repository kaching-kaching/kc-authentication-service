package com.kaching.kcauthenticationservice.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class AppConfig {

    @Value("${config.google.refreshToken.path}")
    private String googleRefreshTokenPath;

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
}
