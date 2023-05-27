package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserClientRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;

public interface IUserHandler {
    UserResponseDto getUserById(Long id);

    void createOwner(UserRequestDto userRequestDto);

    void createEmployee(UserRequestDto userRequestDto);

    void createAdmin(UserRequestDto userRequestDto);

    void createClient(UserClientRequestDto userClientRequestDto);
}
