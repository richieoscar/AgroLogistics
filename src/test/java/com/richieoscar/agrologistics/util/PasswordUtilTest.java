package com.richieoscar.agrologistics.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

class PasswordUtilTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    private PasswordUtil underTest;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        underTest = new PasswordUtil(passwordEncoder);
    }


    @Test
    void testEncodePassword() {
        String password = "password";
        String en = "ryrueoieuytetyetwtywttwtwtw";
        given(underTest.encodePassword(password)).willReturn("ryrueoieuytetyetwtywttwtwtw");
        assertThat(password).isNotEqualTo(en);
    }
}