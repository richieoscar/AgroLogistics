package com.richieoscar.agrologistics.service.impl;

import com.richieoscar.agrologistics.dto.DefaultApiResponse;
import com.richieoscar.agrologistics.dto.StaffDTO;
import com.richieoscar.agrologistics.dto.auth.LoginRequest;
import com.richieoscar.agrologistics.dto.auth.LoginResponse;
import com.richieoscar.agrologistics.dto.auth.SignUpRequest;
import com.richieoscar.agrologistics.dto.auth.SignUpResponse;
import com.richieoscar.agrologistics.exception.AuthenticationException;
import com.richieoscar.agrologistics.repository.StaffRepository;
import com.richieoscar.agrologistics.service.AuthService;
import com.richieoscar.agrologistics.service.StaffService;
import com.richieoscar.agrologistics.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private StaffService staffService;

    @Autowired
    private StaffRepository staffRepository;

    @Override
    public DefaultApiResponse login(LoginRequest loginRequest) {
        log.info("Authenticate User with Credentials {}", loginRequest);
        DefaultApiResponse defaultApiResponse = new DefaultApiResponse();
        String jwtToken = null;
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        try {
            log.info("Authenticating User");
            authenticationManager.authenticate(userToken);
            log.info("Authentication Successful");

        } catch (Exception e) {
            log.error("Error Occurred while authenticating {}", e.getMessage());
            throw new AuthenticationException("Invalid Credentials");
        }
        log.info("Generating Token");
        jwtToken = jwtUtil.generateToken(loginRequest.getEmail());
        log.info("Token Generated");
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccessToken(jwtToken);
        defaultApiResponse.setStatus("success");
        defaultApiResponse.setMessage("Login Successful");
        defaultApiResponse.setData(loginResponse);
        return defaultApiResponse;
    }

    @Override
    public DefaultApiResponse signUp(SignUpRequest signUpRequest) {
        DefaultApiResponse defaultApiResponse = new DefaultApiResponse();
        StaffDTO staffDTO = staffService.saveStaff(signUpRequest);
        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setFirstName(staffDTO.getFirstName());
        signUpResponse.setStaffId(staffDTO.getId());
        signUpResponse.setLastName(staffDTO.getLastName());
        defaultApiResponse.setStatus("success");
        defaultApiResponse.setMessage("Sign Up Successful");
        defaultApiResponse.setData(signUpResponse);
        return defaultApiResponse;
    }
}
