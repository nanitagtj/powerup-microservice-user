package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UserNotFoundException;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserUpdateRequestDto;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.BadRequestException;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IRolePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.domain.validations.UserValidations;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
    }
    public User createUser(User user, String userType) {
        Role role = getRoleForUserType(userType);
        user.setRole(role);

        if (role.getId() != Constants.CLIENT_ROLE_ID) {
            UserValidations.validateAge(user.getBirthdate());
        }

        UserValidations.validateName(user.getName());
        UserValidations.validateEmail((user.getMail()));
        UserValidations.validatePhoneNumber(user.getPhone());
        UserValidations.validateDni(user.getDniNumber());
        UserValidations.validatePassword(user.getPassword());
        UserValidations.validateDateInPast(user.getBirthdate());
        userPersistencePort.saveUser(user);

        return user;
    }
    private Role getRoleForUserType(String userType) {
        if (Constants.ADMIN_NAME.equalsIgnoreCase(userType)) {
            return new Role(Constants.ADMIN_ROLE_ID, Constants.ADMIN_NAME, Constants.ADMIN_DESCRIPTION);
        } else if (Constants.OWNER_NAME.equalsIgnoreCase(userType)) {
            return new Role(Constants.OWNER_ROLE_ID, Constants.OWNER_NAME, Constants.OWNER_DESCRIPTION);
        } else if (Constants.EMPLOYEE_NAME.equalsIgnoreCase(userType)) {
            return new Role(Constants.EMPLOYEE_ROLE_ID, Constants.EMPLOYEE_NAME, Constants.EMPLOYEE_DESCRIPTION);
        } else if (Constants.CLIENT_NAME.equalsIgnoreCase(userType)) {
            return new Role(Constants.CLIENT_ROLE_ID, Constants.CLIENT_NAME, Constants.CLIENT_DESCRIPTION);
        } else {
            throw new BadRequestException(Constants.INVALID_DESCRIPTION + userType);
        }
    }

    @Override
    public User getUserById(Long id) {
        return userPersistencePort.getUserById(id);
    }

    @Override
    public User deleteUser(Long userId) {
        User user = userPersistencePort.getUserById(userId);
        if (user != null) {
            userPersistencePort.deleteUser(user);
        }
        return user;
    }

    @Override
    public User updateUser(Long userId, UserUpdateRequestDto userUpdateRequestDto) {
        User user = userPersistencePort.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }

        UserValidations.validateEmail(userUpdateRequestDto.getMail());
        UserValidations.validatePhoneNumber(userUpdateRequestDto.getPhone());

        user.setMail(userUpdateRequestDto.getMail());
        user.setPhone(userUpdateRequestDto.getPhone());


        userPersistencePort.saveUser(user);

        return user;
    }
}
