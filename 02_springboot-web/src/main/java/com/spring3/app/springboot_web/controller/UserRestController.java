package com.spring3.app.springboot_web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring3.app.springboot_web.model.dto.UserDto;
import com.spring3.app.springboot_web.model.entity.User;

@RestController
@RequestMapping("/api")
public class UserRestController {

    //forma 1
    @GetMapping("/details")
    public Map<?,?> details(){
        Map<String, Object> body = new HashMap<>();
        body.put("title", "Hola! ...");
        body.put("name", "Androide");
        body.put("lastName", "18");
        return body;
    }

    @GetMapping("/details2")
    public Map<?,?> details2(){
        User user = new User("Androide", "17");
        Map<String, Object> body = new HashMap<>();
        body.put("title", "Hola! ...");
        body.put("user", user);
        return body;
    }

    @GetMapping("/details3")
    public UserDto details3(){
        User user = new User("Androide", "16");
        UserDto dto = new UserDto();
        dto.setTitle("rest con dto");
        dto.setUser(user);
        return dto;
    }

    @GetMapping("/list")
    public List<User>getUserList() {
        User user = new User("Androide", "16");
        User user2 = new User("Androide", "17");
        User user3 = new User("Androide", "18");
        List<User> userlList = new ArrayList<>();
        userlList.add(user);
        userlList.add(user2);
        userlList.add(user3);
        return userlList;
    }
    
    
}
