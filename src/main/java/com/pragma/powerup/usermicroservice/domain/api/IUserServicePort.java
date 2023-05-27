package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IUserServicePort {
    void createOwner(User owner);
    void createEmployee(User employee);
    void createClient(User client);
    User getUserById(Long id);
    void createAdmin(User user);
}
