package br.com.apirest.gym.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO(
        @NotNull
        Long id,
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String dateOfBirth,
        @NotBlank
        String cpf,
        @NotBlank
        String password
) {

}
