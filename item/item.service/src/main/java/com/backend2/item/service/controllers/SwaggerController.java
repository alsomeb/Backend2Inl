package com.backend2.item.service.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerController {

    @GetMapping("/")
    public String redirectSwagger() {
        return "redirect:/swagger-ui.html";
    }

}
