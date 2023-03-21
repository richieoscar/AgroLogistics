package com.richieoscar.agrologistics.dto;

import lombok.Data;

@Data
public  abstract class BaseResponse<T> {

    private String status;
    private String message;
    private  T data;
}
