package com.ecom.Ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class CustomerController {
    @GetMapping("home")
    public String home() {
        return "Home";
    }
}
