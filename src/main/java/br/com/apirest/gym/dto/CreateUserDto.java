package br.com.apirest.gym.dto;

import br.com.apirest.gym.entities.Roles.RoleName;

public record CreateUserDto(
    String name,
    String email,
    String dateOfBirth,
    String cpf,
    String password,
    RoleName role
)
{}
