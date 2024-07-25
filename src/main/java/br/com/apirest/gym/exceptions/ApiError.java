package br.com.apirest.gym.exceptions;

import lombok.Builder;

import java.util.List;

@Builder
public record ApiError(
        Integer code,

        String status,

        List<String> errors
) {
}
