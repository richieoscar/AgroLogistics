package com.richieoscar.agrologistics.dto.auth;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class SignUpRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;
    @NotBlank
    private String lastName;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;
}
