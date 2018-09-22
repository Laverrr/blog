package com.laver.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodePwd {
    public static String getEncodePassword(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePwd = encoder.encode(password);
        return encodePwd;
    }

    ///获取加密后的密码 以便在数据库中重置密码
    public static void main(String[] args) {
        System.out.println(getEncodePassword("123456"));
    }
}
