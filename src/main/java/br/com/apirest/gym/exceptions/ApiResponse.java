package br.com.apirest.gym.exceptions;

import lombok.Builder;
@Builder
public record ApiResponse(
        String message
) {}
