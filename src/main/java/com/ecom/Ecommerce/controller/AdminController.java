package com.ecom.Ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {


    @GetMapping("/dashboard")
    public String dashboard() {
        return "AdminDashboard";
    }
}