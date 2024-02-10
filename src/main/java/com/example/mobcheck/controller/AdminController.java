package com.example.mobcheck.controller;

import com.example.mobcheck.service.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UsersServices usersServices;
    @GetMapping("/users")
    public ResponseEntity<?> listAllUsers(){
        return usersServices.getAllUsers();
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<?> changeAccountStatus(@PathVariable Long id){
        return usersServices.changeUserStatus(id);
    }
}
