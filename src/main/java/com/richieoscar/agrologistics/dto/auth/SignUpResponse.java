package com.richieoscar.agrologistics.dto.auth;

import com.richieoscar.agrologistics.dto.BaseResponse;
import lombok.Data;

@Data
public class SignUpResponse {
    private String firstName;
    private String lastName;
    private Long staffId;
}
