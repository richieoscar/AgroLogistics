package com.richieoscar.agrologistics.dto;

import com.richieoscar.agrologistics.enumeration.Role;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
public class StaffDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;


    private String password;

    private LocalDateTime registeredDate;
    private Role role;
}
