package com.mission.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class
HelloController {

    @Bean
    @GetMapping("/hello")
    public String hello(){
        return "popin1";
    }

}
