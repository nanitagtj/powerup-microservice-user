package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponseDto {
    private boolean success = false;
    private String message;
    private BadRequestDto errors;

    public ErrorResponseDto(String message, BadRequestDto errors) {
        this.message = message;
        this.errors = errors;
    }
}
