package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserUpdateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserResponseMapper;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Struct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;
    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userServicePort.getUserById(id);
        return userResponseMapper.userToUSerResponse(user);
    }
    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto, String userType) {
        User user = userServicePort.createUser(userRequestMapper.toUser(userRequestDto), userType);
        return userResponseMapper.userToUSerResponse(user);
    }

    @Override
    public UserResponseDto deleteUser(Long userId, String userType) {
        User user = userServicePort.deleteUser(userRequestMapper.toUserId(userId).getId());
        return userResponseMapper.userToUSerResponse(user);
    }

    @Override
    public UserResponseDto updateUser(Long userId, UserUpdateRequestDto userUpdateRequestDto, String userType) {
        User user = userServicePort.updateUser(userRequestMapper.toUserId(userId).getId(), userUpdateRequestDto);
        return userResponseMapper.userToUSerResponse(user);
    }

    @Override
    public List<UserResponseDto> getUsers(int page, int pageSize) {
        List<User> users = userServicePort.getUsers(page, pageSize);
        return userResponseMapper.usersToUserResponse(users);
    }
}
