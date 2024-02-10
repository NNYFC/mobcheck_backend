package com.example.mobcheck.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UsersDto {
    private String firstname;
    private String lastname;
    private String email;
    private String password;

}
