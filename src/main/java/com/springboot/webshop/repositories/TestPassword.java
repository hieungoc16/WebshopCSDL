package com.springboot.webshop.repositories;

import com.springboot.webshop.models.Users;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestPassword {
    public static void main(String[] args){

        String password = new BCryptPasswordEncoder().encode("123456");
    }
}
