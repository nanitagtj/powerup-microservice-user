package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
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
    public void createAdmin(User admin) {
        createUserWithRole(admin, Constants.ADMIN_ROLE_ID);
    }
    public void createOwner(User owner) {
        createUserWithRole(owner, Constants.OWNER_ROLE_ID);
    }
    public void createEmployee(User employee) {
        createUserWithRole(employee, Constants.EMPLOYEE_ROLE_ID);
    }
    public void createClient(User client) {
        createUserWithRole(client, Constants.CLIENT_ROLE_ID);
    }

    private void createUserWithRole(User user, Long idRole){
        if (idRole != Constants.CLIENT_ROLE_ID){
            UserValidations.validateAge(user.getBirthdate());
        }
        Role role = rolePersistencePort.getRoleById(idRole);
        user.setRole(role);
        userPersistencePort.saveUser(user);
    }
    @Override
    public User getUserById(Long id) {
        return userPersistencePort.getUserById(id);
    }
}
