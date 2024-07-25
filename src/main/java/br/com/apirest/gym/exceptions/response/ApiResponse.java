package br.com.apirest.gym.exceptions.response;

import lombok.Builder;


@Builder
public record ApiResponse(
        String message
) {}
