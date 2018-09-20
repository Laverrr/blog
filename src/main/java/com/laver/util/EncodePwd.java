package com.laver.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodePwd {
    public static String getEncodePassword(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePwd = encoder.encode(password);
        return encodePwd;
    }

    public static void main(String[] args) {
        System.out.println(getEncodePassword("admin"));
    }
}
