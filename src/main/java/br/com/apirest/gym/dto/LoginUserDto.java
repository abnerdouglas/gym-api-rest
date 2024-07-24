package br.com.apirest.gym.dto;

public record LoginUserDto(
        String email,
        String password
) {
}
