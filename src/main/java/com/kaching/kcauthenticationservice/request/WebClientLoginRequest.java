package com.kaching.kcauthenticationservice.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public class WebClientLoginRequest {
    private String email;
    private String password;
    private Boolean returnSecureToken;
}
