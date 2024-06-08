package com.springboot.webshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class UserController {
    @GetMapping("/logon")
    public String showAdminPage() {
        return "login/login";
    }
}

