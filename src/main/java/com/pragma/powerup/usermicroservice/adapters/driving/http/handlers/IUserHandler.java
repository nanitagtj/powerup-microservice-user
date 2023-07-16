package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserUpdateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;

public interface IUserHandler {
    UserResponseDto getUserById(Long id);

    UserResponseDto createUser(UserRequestDto userRequestDto, String userType);

    UserResponseDto deleteUser(Long userId, String userType);

    UserResponseDto updateUser(Long userId, UserUpdateRequestDto userUpdateRequestDto, String userType);
}
