package com.tech.onlineshopping01.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.onlineshopping01.module.online_User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    ObjectMapper obj = new ObjectMapper();
    HashMap<String, online_User> hash = new HashMap<>();
    @Resource
    online_User onlineUser;
    @PostMapping("/users")
    public String add(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail, Map<String, Object> resultMap) throws JsonProcessingException {
        online_User onlineUser = new online_User(userName, userEmail);
        hash.put(userEmail, onlineUser);
        resultMap.put("user", onlineUser);
        return "userAAA";
    }
    @GetMapping("/users/{userEmail}")
    public String get(@PathVariable("userEmail") String email, Map<String, Object> resultMap) {
        online_User onlineUser = hash.getOrDefault(email,this.onlineUser);
        resultMap.put("user", onlineUser);
        return "userAAA";

    }
}
