package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LandingController {

    @GetMapping("/landing")
    public String landingPage() {
        return "landing";
    }
}
