package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserClientRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserRequestMapper userResponseMapper;

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userServicePort.getUserById(id);
        return userResponseMapper.toResponse(user);
    }
    @Override
    public void createAdmin(UserRequestDto userRequestDto) {
        userServicePort.createAdmin(userRequestMapper.toUser(userRequestDto));
    }
    @Override
    public void createOwner(UserRequestDto userRequestDto) {
        userServicePort.createOwner(userRequestMapper.toUser(userRequestDto));
    }

    @Override
    public void createEmployee(UserRequestDto userRequestDto) {
        userServicePort.createEmployee(userRequestMapper.toUser(userRequestDto));
    }

    @Override
    public void createClient(UserClientRequestDto userClientRequestDto) {
        userServicePort.createClient(userRequestMapper.toUserClient(userClientRequestDto));
    }
}
