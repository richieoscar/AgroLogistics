package com.richieoscar.agrologistics.controllers;

import com.richieoscar.agrologistics.dto.DefaultApiResponse;
import com.richieoscar.agrologistics.dto.auth.LoginRequest;
import com.richieoscar.agrologistics.dto.auth.LoginResponse;
import com.richieoscar.agrologistics.dto.auth.SignUpRequest;
import com.richieoscar.agrologistics.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<DefaultApiResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("AuthenticationController::login");
        DefaultApiResponse loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<DefaultApiResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        log.info("AuthenticationController::signUp");
        DefaultApiResponse signUpResponse = authService.signUp(signUpRequest);
        return ResponseEntity.ok(signUpResponse);
    }
}
