package com.kaching.kcauthenticationservice;

import com.kaching.libidentity.annotation.EnableKcIdentity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableKcIdentity
@SpringBootApplication
public class KcAuthenticationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KcAuthenticationServiceApplication.class, args);
    }
}
