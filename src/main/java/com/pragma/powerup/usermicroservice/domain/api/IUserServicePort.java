package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserUpdateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IUserServicePort {
    User createUser(User user, String userType);
    User getUserById(Long id);
    User deleteUser(Long userId);
    User updateUser(Long userId, UserUpdateRequestDto userUpdateRequestDto);
}
