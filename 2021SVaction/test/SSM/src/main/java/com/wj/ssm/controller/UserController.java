package com.wj.ssm.controller;

import com.wj.ssm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wj.ssm.service.UserService;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") Integer id){
        System.out.println("cascascasca");
        return userService.findById(id);
    }

    @RequestMapping("findByName/{name}")
    public ModelAndView findByName(@PathVariable("name") String name) {
        User user = userService.findByName(name);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",user);
        modelAndView.setViewName("/index.jsp");
        return modelAndView;
    }
}
