package com.example.Intenship_Project.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private String email;
    private String role;
    private String fullName;


}
