package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@Data
public class LoginRequestDto {

    private String mail;
    @NotBlank
    private String password;
}
