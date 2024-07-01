package com.spring3.app.springboot_web.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.spring3.app.springboot_web.model.entity.User;

@Controller
public class UserController {

    //forma 1
    /* 
    @GetMapping("/details")
    public String details(Model model){
        model.addAttribute("title", "Hola! ...");
        model.addAttribute("name", "Androide");
        model.addAttribute("lastName", "18");
        return "details";
    }
    */
    

    //forma 2
    /* 
    @GetMapping("/details")
    public String details(Map<String,String> model){
        model.put("title", "Hola! ...");
        model.put("name", "Androide");
        model.put("lastName", "18");
        return "details";
    }
    */

    //forma 3

    @GetMapping("/details")
    public String details(Model model){
        User user = new User("Androide", "17");
        model.addAttribute("title", "Hola! ...");
        model.addAttribute("user", user);
        return "details";
    }

    @GetMapping("/list")
    public String getUserList(ModelMap modelMap) {
        User user = new User("Androide", "16", "xxx@zzz");
        User user2 = new User("Androide", "17", null);
        User user3 = new User("Androide", "18", "xxx@zzz");
        List<User> userlList = Arrays.asList(user, user2, user3);
        modelMap.addAttribute("userlList", userlList);
        modelMap.addAttribute("title", "lista de usuarios");
        return "list";
    }

    //con esta anotación pasamos el obj q devuelve a todas las vistas donde se quiera usar.
    @ModelAttribute("nombre")
    public String nombre(){
        return "Majin Boo";
    }
    
}
