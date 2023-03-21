package com.richieoscar.agrologistics.domain;

import com.richieoscar.agrologistics.enumeration.Role;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;

    @Column
    private String password;
    @Enumerated(EnumType.STRING)
    @Column
    private Role role;
    @Column
    private LocalDateTime registeredDate;
}
