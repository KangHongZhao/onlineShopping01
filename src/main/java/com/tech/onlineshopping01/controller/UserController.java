package com.tech.onlineshopping01.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.onlineshopping01.module.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    ObjectMapper obj = new ObjectMapper();
    HashMap<String, User> hash = new HashMap<>();
    @Resource
    User user;
    @PostMapping("/users")
    public String add(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail, Map<String, Object> resultMap) throws JsonProcessingException {
        User user = new User(userName, userEmail);
        hash.put(userEmail, user);
        resultMap.put("user", user);
        return "userAAA";
    }
    @GetMapping("/users/{userEmail}")
    public String get(@PathVariable("userEmail") String email, Map<String, Object> resultMap) {
        User user = hash.getOrDefault(email,this.user);
        resultMap.put("user",user);
        return "userAAA";

    }
}
