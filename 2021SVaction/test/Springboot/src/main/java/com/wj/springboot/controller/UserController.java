package com.wj.springboot.controller;

import com.wj.springboot.entity.User;
import com.wj.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findById")
    public User findById(@RequestParam("id") Integer id) {
        User user = userService.findById(id);
        System.out.println(user);
        return user;
    }

    @RequestMapping("/updateById")
    public void updateById(Integer id, String nickname) {
        userService.updateById(id, nickname);
        int a = 10 / 0;
    }
}
