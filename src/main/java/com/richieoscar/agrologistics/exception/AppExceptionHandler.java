package com.richieoscar.agrologistics.exception;

import com.richieoscar.agrologistics.dto.DefaultApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public DefaultApiResponse handleInvalidArgument(MethodArgumentNotValidException ex) {
        DefaultApiResponse apiResponse = new DefaultApiResponse();
        Map<String, String> errorMap = new HashMap<>();
        ex
                .getBindingResult()
                .getFieldErrors()
                .forEach(error -> {
                    errorMap.put(error.getField(), error.getDefaultMessage());
                });
        apiResponse.setStatus("Failed");
        apiResponse.setMessage("Field validation failed");
        apiResponse.setData(errorMap);
        return apiResponse;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public DefaultApiResponse handleAuthenticationException(AuthenticationException exception) {
        DefaultApiResponse defaultApiResponse = new DefaultApiResponse();
        defaultApiResponse.setMessage(exception.getMessage());
        defaultApiResponse.setStatus("Failed");
        return defaultApiResponse;
    }

    @ExceptionHandler
    public DefaultApiResponse handleStaffException(StaffException exception) {
        DefaultApiResponse defaultApiResponse = new DefaultApiResponse();
        defaultApiResponse.setMessage(exception.getMessage());
        defaultApiResponse.setStatus("Failed");
        return defaultApiResponse;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(org.springframework.security.core.AuthenticationException.class)
    public DefaultApiResponse handleUnauthorizedException(org.springframework.security.core.AuthenticationException ex) {
        DefaultApiResponse response = new DefaultApiResponse();
        response.setStatus("Failed");
        response.setMessage("Unauthorized to access this resource.");
        return response;
    }

}
