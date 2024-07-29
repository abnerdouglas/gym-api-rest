package br.com.apirest.gym.dto;

import br.com.apirest.gym.entities.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RecoveryUserDto(
        @NotBlank
        Long id,
        @NotBlank
        String email,
        @NotNull
        List<Role> roles
) {
}
