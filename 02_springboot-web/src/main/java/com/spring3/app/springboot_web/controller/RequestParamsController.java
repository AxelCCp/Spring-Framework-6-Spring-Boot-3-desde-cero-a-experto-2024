package com.spring3.app.springboot_web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring3.app.springboot_web.model.dto.ParamDto;
import com.spring3.app.springboot_web.model.dto.ParamDtoMix;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/params")
public class RequestParamsController {

    //http://localhost:8080/api/params/foo?message=holaaaaa!
    @GetMapping("/foo")
    public ParamDto foo(@RequestParam(required = false, defaultValue = "hola q tal!") String message) {
        ParamDto dto = new ParamDto();
        dto.setMessage(message);
        System.out.println(dto.getMessage());
        return dto;
    }

    //http://localhost:8080/api/params/bar?text=zzzzzzz&code=7654
    @GetMapping("/bar")
    public ParamDtoMix bar(@RequestParam(defaultValue = "xxxxxxx") String text, @RequestParam(defaultValue = "12345") Integer code) {
        ParamDtoMix dto = new ParamDtoMix();
        dto.setMessage(text);
        dto.setCode(code);
        return dto;
    } 

    //Obteniendo los parametros de forma nativa
    //http://localhost:8080/api/params/req?code=7777&message=jenna
    @GetMapping("/req")
    public ParamDtoMix request(HttpServletRequest request) {
        ParamDtoMix dto = new ParamDtoMix();
        dto.setCode(Integer.parseInt(request.getParameter("code")));
        dto.setMessage(request.getParameter("message"));
        return dto;
    }


    



}
