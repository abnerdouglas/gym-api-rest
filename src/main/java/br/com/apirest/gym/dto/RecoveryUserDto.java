package br.com.apirest.gym.dto;

import br.com.apirest.gym.entities.Role;

import java.util.List;

public record RecoveryUserDto(
        Long id,
        String email,
        List<Role> roles
) {
}
