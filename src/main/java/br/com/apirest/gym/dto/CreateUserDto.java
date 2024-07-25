package br.com.apirest.gym.dto;

import br.com.apirest.gym.entities.Roles.RoleName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDto(
    @NotNull
    @NotBlank
    String name,
    @NotNull
    @NotBlank
    String email,
    @NotNull
    @NotBlank
    String dateOfBirth,
    @NotNull
    @NotBlank
    String cpf,
    @NotNull
    @NotBlank
    String password,
    @NotNull
    RoleName role
)
{}
