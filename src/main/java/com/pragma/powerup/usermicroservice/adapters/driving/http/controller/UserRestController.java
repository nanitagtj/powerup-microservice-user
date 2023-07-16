package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserUpdateRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.ApiResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
public class UserRestController {
    private final IUserHandler userHandler;

    @Operation(summary = "Create a new User",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User created",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))),
                    @ApiResponse(responseCode = "409", description = "User already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> createUser(@Validated @RequestBody UserRequestDto userRequestDto, String userType, HttpServletRequest request) {
        UserResponseDto userResponseDto = userHandler.createUser(userRequestDto, userType);
        ApiResponseDto<UserResponseDto> response = new ApiResponseDto<>();
        response.setSuccess(true);
        response.setMessage(Constants.USER_CREATED_MESSAGE);
        response.setData(userResponseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Delete an user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Deleted user",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "User not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))
            })
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> deleteUser(@PathVariable("userId") Long userId, @RequestParam("userType") String userType) {
        UserResponseDto userResponseDto = userHandler.deleteUser(userId, userType);
        ApiResponseDto<UserResponseDto> response = new ApiResponseDto<>();
        response.setSuccess(true);
        response.setMessage(Constants.USER_DELETED_MESSAGE);
        response.setData(userResponseDto);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update/{userId}")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> updateUser(@PathVariable("userId") Long userId, @Validated @RequestBody UserUpdateRequestDto userUpdateRequestDto, @RequestParam("userType") String userType) {
        UserResponseDto userResponseDto = userHandler.updateUser(userId, userUpdateRequestDto, userType);
        ApiResponseDto<UserResponseDto> response = new ApiResponseDto<>();
        response.setSuccess(true);
        response.setMessage(Constants.USER_UPDATED_MESSAGE);
        response.setData(userResponseDto);
        return ResponseEntity.ok(response);
    }



    @Operation(summary = "Get user by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "user returned",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class)))),
                    @ApiResponse(responseCode = "404", description = "No data found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/find/{id}")
    public UserResponseDto getUserById(@PathVariable @Validated Long id) {
        return userHandler.getUserById(id);
    }
}
