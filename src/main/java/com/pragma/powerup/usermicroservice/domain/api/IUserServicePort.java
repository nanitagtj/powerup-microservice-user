package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IUserServicePort {
    void createOwner(User owner);
    void createEmployee(User employee);
    User getUserById(Long id);
    void createAdmin(User user);
}
