package br.com.apirest.gym.dto;

import jakarta.validation.constraints.NotBlank;

public record RecoveryJwtTokenDto(
        @NotBlank
        String token
) {
}
