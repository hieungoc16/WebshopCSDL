package com.springboot.webshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControllers {

    @GetMapping("/")
    public String showCustomPage() {
        return "giaodiennguoidung/customer";
    }
}

