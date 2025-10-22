package com.example.springsec.controllers;

import com.example.springsec.Service.UsersService;
import com.example.springsec.entity.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsersController {

    private UsersService usersService;

    public UsersController(UsersService usersService){
        this.usersService=usersService;
    }

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody Users user){
        return ResponseEntity.ok(usersService.save(user));
    }

}
