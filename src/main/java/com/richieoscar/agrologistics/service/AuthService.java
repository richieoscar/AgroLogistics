package com.richieoscar.agrologistics.service;

import com.richieoscar.agrologistics.dto.DefaultApiResponse;
import com.richieoscar.agrologistics.dto.auth.LoginRequest;
import com.richieoscar.agrologistics.dto.auth.LoginResponse;
import com.richieoscar.agrologistics.dto.auth.SignUpRequest;
import com.richieoscar.agrologistics.dto.auth.SignUpResponse;

public interface AuthService {

    DefaultApiResponse login(LoginRequest loginRequest);

    DefaultApiResponse signUp(SignUpRequest signUpRequest);
}
