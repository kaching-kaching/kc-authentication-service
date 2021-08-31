package com.kaching.kcauthenticationservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    public ResponseEntity<Object> responseOk() {
        return ResponseEntity.ok().build();
    }

    public <T> ResponseEntity<T> responseOk(T body) {
        return ResponseEntity.ok(body);
    }

    public ResponseEntity<Object> responseCreated() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
