package com.richieoscar.agrologistics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RouteNotAvailableException extends RuntimeException {

    public RouteNotAvailableException(String message) {
        super(message);
    }
}
