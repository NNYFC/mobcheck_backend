package com.example.mobcheck.service;

import com.example.mobcheck.dto.UsersDto;
import com.example.mobcheck.model.Roles;
import com.example.mobcheck.model.Users;
import com.example.mobcheck.repository.RolesRepository;
import com.example.mobcheck.repository.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsersServicesTests {
    @Mock
    private UsersRepository usersRepository;
    @Mock
    private RolesRepository rolesRepository;

    @InjectMocks
    private UsersServicesImp usersServicesImp;

    @Test
    public void addUser(){
        UsersDto usersDto = UsersDto.builder().firstname("first name").lastname("last name")
                .email("email@email.com").password("password").build();

        Roles roles = Roles.builder().name("USER").build();

        when(rolesRepository.findByName("USER")).thenReturn(Optional.ofNullable(roles));

        ResponseEntity<?> responseEntity = usersServicesImp.addUser(usersDto);
        Assertions.assertEquals(201, responseEntity.getStatusCode().value());
    }



    @Test
    public void getAllUsers(){
        List<Users> usersList = Mockito.mock();

        when(usersRepository.findAll()).thenReturn(usersList);
        ResponseEntity<?> response = usersServicesImp.getAllUsers();

        Assertions.assertEquals(200, response.getStatusCode().value());
    }
}
