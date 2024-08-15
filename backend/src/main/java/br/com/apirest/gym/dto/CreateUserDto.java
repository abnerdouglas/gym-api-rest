package br.com.apirest.gym.dto;

import br.com.apirest.gym.models.Roles.RoleName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDto(

    @NotBlank
    String name,
    @NotBlank
    String email,
    @NotBlank
    String dateOfBirth,
    @NotBlank
    String cpf,
    @NotBlank
    String password,
    @NotNull
    RoleName role
)
{}
