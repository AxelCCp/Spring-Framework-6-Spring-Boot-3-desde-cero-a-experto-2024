package com.spring3.app.springboot_web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring3.app.springboot_web.model.dto.ParamDto;
import com.spring3.app.springboot_web.model.entity.User;

@RestController
@RequestMapping("/api/var")
public class PathVariableController {

    //http://localhost:8080/api/var/baz/{xxxxx}
    @GetMapping("/baz/{message}")
    public ParamDto baz(@PathVariable(name="message") String message) {
        ParamDto dto = new ParamDto();
        dto.setMessage(message);
        return dto;
    }    

    //http://localhost:8080/api/var/mix/zanahoria/1234
    @GetMapping("/mix/{product}/{id}")
    public Map<?, ?> baz(@PathVariable(name="product") String product, @PathVariable(name="id") Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("product", product);
        map.put("id", id);
        return map;
    }

    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return user;
    }


    @GetMapping("/values")
    public Map<?,?> values( @Value("${config.code}") String code) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("username", username);
        map.put("message", message);
        map.put("listOfValues", listOfValues);    
        map.put("listOfValuesString", listOfValuesString);
        map.put("messageUpperCase", messageUpperCase);
        map.put("messageConcat", messageConcat);
        map.put("myMapConfig", myMapConfig);
        map.put("product", product);
        map.put("internalMap", internalMap);
        map.put("ENV_USERNAME", env.getProperty("config.username"));
        map.put("ENV_CODE_INTEGER", Integer.valueOf(env.getProperty("config.code")));
        map.put("ENV_CODE_LONG", env.getProperty("config.code", Long.class));
        map.put("ENV_INTERNAL_MAP", env.getProperty("config.map"));
        return map;
    }

    @Value("${config.username}")
    private String username;
    @Value("${config.message}")
    private String message;
    @Value("${config.listOfValues}")
    private List<String> listOfValues;

    @Value("#{ '${config.listOfValues}'.split(',') } ") //toma la lista y con las '' la pasa a String y luego se le aplica un metodo de String class.
    private String listOfValuesString;

    @Value("#{ '${config.message}'.toUpperCase() } ")
    private String messageUpperCase;

    @Value("#{ '${config.message}'.concat('---- xxxx ----').concat('${config.message}') } ")
    private String messageConcat;

    @Value("#{${config.map}}")
    private Map<String, Object> myMapConfig;

    @Value("#{${config.map}.product}")
    private String product;

    @Value("#{${config.map}.internalMap}")
    private Map<String, String> internalMap;

    @Autowired
    private Environment env;
    
}
