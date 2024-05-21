package com.nguyen1o2.controller;

import com.nguyen1o2.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping()
    public ResponseEntity<?> getAllUser(){
        return new ResponseEntity<>(userServiceImp.getAllUser(), HttpStatus.OK);
    }
}
