package com.everis.d4i.tutorial.controllers;

import com.everis.d4i.tutorial.payload.request.LoginRequest;
import com.everis.d4i.tutorial.payload.request.SignUpRequest;
import org.springframework.http.ResponseEntity;

public interface AuthController {

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

    public ResponseEntity<?> registerUser(SignUpRequest signUpRequest);
}
