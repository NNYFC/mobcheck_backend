package com.example.mobcheck.controller;

import com.example.mobcheck.dto.UsersDto;
import com.example.mobcheck.service.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class UsersController {
    @Autowired
    private UsersServices usersServices;

    @GetMapping("/")
    public ResponseEntity<?> baseUrl(){
        usersServices.addRoleAndAdmin();
        return ResponseEntity.status(200).body("WELCOME TO MobCheck API");
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UsersDto usersDto){
        return usersServices.addUser(usersDto);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<?> listUserData(@PathVariable String email){
        return usersServices.getPersonalData(email);
    }

}
