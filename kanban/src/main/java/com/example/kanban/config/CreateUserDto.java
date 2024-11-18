package com.example.kanban.config;

public record CreateUserDto(

        String email,
        String password,
        RoleName role

) {
}