package com.pragma.powerup.usermicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserClientRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {
    User toUser(UserRequestDto userRequestDto);
    User toUserClient(UserClientRequestDto userClientRequestDto);

    UserResponseDto toResponse(User user);
}
