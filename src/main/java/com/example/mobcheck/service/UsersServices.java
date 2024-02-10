package com.example.mobcheck.service;

import com.example.mobcheck.dto.UsersDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersServices extends UserDetailsService {

    void addRoleAndAdmin();
    ResponseEntity<?> addUser(UsersDto usersDto);
    ResponseEntity<?> getPersonalData(String email);
    ResponseEntity<?> getAllUsers();
    ResponseEntity<?> changeUserStatus(Long id);
}
