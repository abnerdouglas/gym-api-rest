package br.com.apirest.gym.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginUserDto(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
