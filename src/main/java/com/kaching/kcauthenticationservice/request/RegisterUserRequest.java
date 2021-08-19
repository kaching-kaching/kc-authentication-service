package com.kaching.kcauthenticationservice.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterUserRequest {

    @Email
    @NotBlank
    String email;

    @NotBlank
    String username;

    @NotBlank
    String password;
}
