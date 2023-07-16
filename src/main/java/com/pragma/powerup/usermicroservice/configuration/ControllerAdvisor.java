package com.pragma.powerup.usermicroservice.configuration;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.*;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.BadRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.ErrorResponseDto;
import com.pragma.powerup.usermicroservice.domain.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.pragma.powerup.usermicroservice.configuration.Constants.*;

@ControllerAdvice
public class ControllerAdvisor {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                errorMessages.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            } else {
                errorMessages.add(error.getDefaultMessage());
            }
        }
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponseDto> handleAuthenticationException(AuthenticationException exception) {
        BadRequestDto error = new BadRequestDto(AUTHENTICATION_ERROR, CREDENTIALS, WRONG_CREDENTIALS_MESSAGE);
        ErrorResponseDto responseDto = new ErrorResponseDto(BAD_REQUEST, (error));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNoDataFoundException(NoDataFoundException exception) {
        BadRequestDto error = new BadRequestDto(DATA_NOT_FOUND_ERROR, RESOURCE, NO_DATA_FOUND_MESSAGE);
        ErrorResponseDto responseDto = new ErrorResponseDto(BAD_REQUEST, (error));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    @ExceptionHandler(MailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleMailAlreadyExistsException(MailAlreadyExistsException ex) {
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(MAIL_ALREADY_EXISTS_MESSAGE);
        responseDto.setErrors(new BadRequestDto(VALIDATION_FAILS, MAIL, MAIL_ALREADY_EXISTS_MESSAGE));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @ExceptionHandler(RoleNotAllowedForCreationException.class)
    public ResponseEntity<ErrorResponseDto> handleRoleNotAllowedForCreationException(RoleNotAllowedForCreationException ex) {
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(ROLE_NOT_ALLOWED_MESSAGE);
        responseDto.setErrors(new BadRequestDto(VALIDATION_FAILS, ROLE, ROLE_NOT_ALLOWED_MESSAGE));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(USER_ALREADY_EXISTS_MESSAGE);
        responseDto.setErrors(new BadRequestDto(VALIDATION_FAILS, USER_DNI, USER_ALREADY_EXISTS_MESSAGE));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(USER_NOT_FOUND_MESSAGE);
        responseDto.setErrors(new BadRequestDto(VALIDATION_FAILS, USER, USER_NOT_FOUND_MESSAGE));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleRoleNotFoundException(RoleNotFoundException ex) {
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(ROLE_NOT_FOUND_MESSAGE);
        responseDto.setErrors(new BadRequestDto(VALIDATION_FAILS, ROLE, ROLE_NOT_FOUND_MESSAGE));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    @ExceptionHandler(InvalidAgeException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidAgeException(InvalidAgeException ex) {
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(INVALID_AGE_EXCEPTION);
        responseDto.setErrors(new BadRequestDto(VALIDATION_FAILS, AGE, INVALID_AGE_EXCEPTION));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponseDto> handleUnauthorizedException(UnauthorizedException ex) {
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(UNAUTHORIZED_EXCEPTION);
        responseDto.setErrors(new BadRequestDto(VALIDATION_FAILS, ROLE, UNAUTHORIZED_EXCEPTION));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
    @ExceptionHandler(InvalidNameException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidNameException(InvalidNameException ex) {
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(INVALID_NAME_EXCEPTION);
        responseDto.setErrors(new BadRequestDto(VALIDATION_FAILS, USER_NAME, INVALID_NAME_EXCEPTION));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidPasswordException(InvalidPasswordException ex) {
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(INVALID_PASSWORD_EXCEPTION);
        responseDto.setErrors(new BadRequestDto(VALIDATION_FAILS, USER_PASSWORD, INVALID_PASSWORD_EXCEPTION));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

    @ExceptionHandler(InvalidMailException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidMailException(InvalidMailException ex) {
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(INVALID_MAIL_EXCEPTION);
        responseDto.setErrors(new BadRequestDto(VALIDATION_FAILS, USER_MAIL, INVALID_MAIL_EXCEPTION));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
    @ExceptionHandler(InvalidPhoneException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidPhoneException(InvalidPhoneException ex) {
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(INVALID_PHONE_EXCEPTION);
        responseDto.setErrors(new BadRequestDto(VALIDATION_FAILS, USER_PHONE, INVALID_PHONE_EXCEPTION));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
    @ExceptionHandler(InvalidDniException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidDniException(InvalidDniException ex) {
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(INVALID_DNI_EXCEPTION);
        responseDto.setErrors(new BadRequestDto(VALIDATION_FAILS, USER_DNI, INVALID_DNI_EXCEPTION));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
    @ExceptionHandler(BirthdayPastException.class)
    public ResponseEntity<ErrorResponseDto> handleBirthdayPastException(BirthdayPastException ex) {
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(INVALID_BIRTHDATE_EXCEPTION);
        responseDto.setErrors(new BadRequestDto(VALIDATION_FAILS, USER_BIRTHDATE, INVALID_BIRTHDATE_EXCEPTION));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
    @ExceptionHandler(BirthdateRequiredException.class)
    public ResponseEntity<ErrorResponseDto> handleBirthdateRequiredException(BirthdateRequiredException ex) {
        ErrorResponseDto responseDto = new ErrorResponseDto();
        responseDto.setMessage(INVALID_BIRTHDATE_EXCEPTION);
        responseDto.setErrors(new BadRequestDto(VALIDATION_FAILS, USER_BIRTHDATE, INVALID_BIRTHDATE_EXCEPTION));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }


}
