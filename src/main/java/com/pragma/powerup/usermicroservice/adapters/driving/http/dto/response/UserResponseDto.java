package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class UserResponseDto {
    @NotNull
    private Long id;
    @NotNull
    private String dniNumber;
    @Email
    @NotNull
    private String mail;
    @NotNull
    private String name;
    @NotNull
    private String phone;
    @NotNull
    private String surname;
    @NotNull
    private Long idRole;
    @NotNull
    private LocalDate birthDate;
}
