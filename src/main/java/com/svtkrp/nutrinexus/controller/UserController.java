package com.svtkrp.nutrinexus.controller;

import com.svtkrp.nutrinexus.domain.UserModel;
import com.svtkrp.nutrinexus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("add")
    public UserModel add() {
        return userService.add();
    }
}
