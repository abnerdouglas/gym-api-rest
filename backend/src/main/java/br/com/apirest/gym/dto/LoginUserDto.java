package br.com.apirest.gym.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginUserDto(
        @NotNull
        @NotBlank
        String email,
        @NotNull
        @NotBlank
        String password
) {
}
