package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import lombok.Data;

@Data
public class UserUpdateRequestDto {
    private String mail;
    private String phone;
}
