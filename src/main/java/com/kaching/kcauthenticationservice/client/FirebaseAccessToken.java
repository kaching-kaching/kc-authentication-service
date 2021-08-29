package com.kaching.kcauthenticationservice.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FirebaseAccessToken {
    String idToken;
    String email;
    String refreshToken;
    String expiresIn;
    String localId;
    Boolean registered;
}
