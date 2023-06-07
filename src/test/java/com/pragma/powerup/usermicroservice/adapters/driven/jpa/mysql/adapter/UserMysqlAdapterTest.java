package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.MailAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UserAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserMysqlAdapterTest {

    private IUserRepository userRepository;
    private IUserEntityMapper userEntityMapper;
    private PasswordEncoder passwordEncoder;
    private UserMysqlAdapter userMysqlAdapter;

    @BeforeEach
    void setUp() {
        userRepository = mock(IUserRepository.class);
        userEntityMapper = mock(IUserEntityMapper.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userMysqlAdapter = new UserMysqlAdapter(userRepository, userEntityMapper, passwordEncoder);
    }

    @Test
    void saveUser_ShouldThrowUserAlreadyExistsException_WhenUserWithDniExists() {
        // Arrange
        User user = new User();
        user.setDniNumber("12345678");
        when(userRepository.findByDniNumber(anyString())).thenReturn(Optional.of(new UserEntity()));

        // Act & Assert
        assertThrows(UserAlreadyExistsException.class, () -> userMysqlAdapter.saveUser(user));
    }

    @Test
    void saveUser_ShouldThrowMailAlreadyExistsException_WhenUserWithMailExists() {
        // Arrange
        User user = new User();
        user.setDniNumber("12345678");
        user.setMail("test@example.com");
        when(userRepository.findByDniNumber(anyString())).thenReturn(Optional.empty());
        when(userRepository.existsByMail(anyString())).thenReturn(true);

        // Act & Assert
        assertThrows(MailAlreadyExistsException.class, () -> userMysqlAdapter.saveUser(user));
    }

    @Test
    void saveUser_ShouldEncodeUserPasswordAndSaveUser_WhenUserIsValid() {
        // Arrange
        User user = new User();
        user.setDniNumber("12345678");
        user.setMail("test@example.com");
        user.setPassword("password");
        UserEntity userEntity = new UserEntity();
        when(userRepository.findByDniNumber(anyString())).thenReturn(Optional.empty());
        when(userRepository.existsByMail(anyString())).thenReturn(false);
        when(userEntityMapper.toEntity(any(User.class))).thenReturn(userEntity);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        // Act
        userMysqlAdapter.saveUser(user);

        // Assert
        verify(passwordEncoder, times(1)).encode("password");
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    void getUserById_ShouldThrowNoDataFoundException_WhenUserEntityNotFound() {
        // Arrange
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> userMysqlAdapter.getUserById(userId));
    }

    @Test
    void getUserById_ShouldReturnUser_WhenUserEntityFound() {
        // Arrange
        Long userId = 1L;
        UserEntity userEntity = new UserEntity();
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(userEntityMapper.userEntityToUser(userEntity)).thenReturn(user);

        // Act
        User result = userMysqlAdapter.getUserById(userId);

        // Assert
        assertEquals(user, result);
    }


}