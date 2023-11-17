package com.vinicius.springhotel.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    String home() {
        return "Welcome home";
    }

    @GetMapping("/success")
    String success() {
        return "Success";
    }

    @GetMapping("/fail")
    String failure() {
        return "Failed";
    }
}
