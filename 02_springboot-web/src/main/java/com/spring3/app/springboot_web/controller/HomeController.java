package com.spring3.app.springboot_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping({"","/", "/home"})
    public String home() {
        //return "redirect:/list";
        return "forward:/list";
    }
}
