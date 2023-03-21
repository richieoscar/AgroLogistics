package com.richieoscar.agrologistics.util;

import com.richieoscar.agrologistics.exception.PasswordDoNotMatchException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class PasswordUtil {

    @Autowired
    private PasswordEncoder passwordEncoder;


    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }


    public static void validatePassword(String password, String confirmPassword) {
       if(!doValidate(password)) throw new PasswordDoNotMatchException("Password must contain at least one UpperCase, Special Character and Digit");
    }

    protected static boolean doValidate(String password) {
        if (
                password.length() > 8 &&
                        containsSpecialCharacter(password) &&
                        containsDigit(password) &&
                        containsDigit(password) &&
                        containsUpperCase(password)
        ) {
            return true;
        }
        return false;
    }

    protected static boolean containsSpecialCharacter(String password) {
        if (
                password.contains("@") ||
                        password.contains("#") ||
                        password.contains("$") ||
                        password.contains("&") ||
                        password.contains("%") ||
                        password.contains("!") ||
                        password.contains("^") ||
                        password.contains("*")
        ) {
            return true;
        }
        return false;
    }

    protected static boolean containsUpperCase(String password) {
        char[] chars = password.toCharArray();
        for (Character ch : chars) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }

    protected static boolean containsDigit(String password) {
        char[] chars = password.toCharArray();
        for (Character ch : chars) {
            if (Character.isDigit(ch)) {
                return true;
            }
        }
        return false;
    }

    public static boolean passwordMatch(String password, String confirmPassword) {
        if (password.equals(confirmPassword)) return true;
        throw new PasswordDoNotMatchException("Password do not match");
    }

}
