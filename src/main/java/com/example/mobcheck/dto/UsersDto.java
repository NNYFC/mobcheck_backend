package com.example.mobcheck.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UsersDto {
    private String firstname;
    private String lastname;
    private String email;
    private String password;

}
