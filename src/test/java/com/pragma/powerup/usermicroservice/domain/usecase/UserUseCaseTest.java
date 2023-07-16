package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IRolePersistencePort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Method;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserUseCaseTest {
    @Mock
    private IUserPersistencePort userPersistencePort;
    @Mock
    private IRolePersistencePort rolePersistencePort;

    private IUserServicePort userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userUseCase = new UserUseCase(userPersistencePort, rolePersistencePort);
    }

    /*@Test
    void createAdmin_ShouldCreateUserWithAdminRole() {
        // Arrange
        User admin = new User();
        admin.setBirthdate(LocalDate.of(1990, 1, 1));

        Role adminRole = new Role(1L, "ROLE_ADMIN", "ROLE_ADMIN");
        when(rolePersistencePort.getRoleById(Constants.ADMIN_ROLE_ID)).thenReturn(adminRole);

        // Act
        userUseCase.createUser(user);

        // Assert
        verify(rolePersistencePort, times(1)).getRoleById(Constants.ADMIN_ROLE_ID);
        verify(userPersistencePort, times(1)).saveUser(admin);
        verifyNoMoreInteractions(rolePersistencePort, userPersistencePort);
    }

    @Test
    void createOwner_ShouldCreateUserWithOwnerRole() {
        // Arrange
        User owner = new User();
        owner.setBirthdate(LocalDate.of(1990, 1, 1));

        Role ownerRole = new Role(2L, "ROLE_OWNER", "ROLE_OWNER");
        when(rolePersistencePort.getRoleById(Constants.OWNER_ROLE_ID)).thenReturn(ownerRole);

        // Act
        userUseCase.createOwner(owner);

        // Assert
        verify(rolePersistencePort, times(1)).getRoleById(Constants.OWNER_ROLE_ID);
        verify(userPersistencePort, times(1)).saveUser(owner);
        verifyNoMoreInteractions(rolePersistencePort, userPersistencePort);
    }

    @Test
    void createEmployee_ShouldCreateUserWithEmployeeRole() {
        // Arrange
        User employee = new User();
        employee.setBirthdate(LocalDate.of(1990, 1, 1));

        Role employeeRole = new Role(3L, "ROLE_EMPLOYEE", "ROLE_EMPLOYEE"); // Aquí se pasan los valores adecuados
        when(rolePersistencePort.getRoleById(Constants.EMPLOYEE_ROLE_ID)).thenReturn(employeeRole);

        // Act
        userUseCase.createEmployee(employee);

        // Assert
        verify(rolePersistencePort, times(1)).getRoleById(Constants.EMPLOYEE_ROLE_ID);
        verify(userPersistencePort, times(1)).saveUser(employee);
        verifyNoMoreInteractions(rolePersistencePort, userPersistencePort);
    }


    @Test
    void createClient_ShouldCreateUserWithClientRole() {
        // Arrange
        User client = new User();
        client.setBirthdate(LocalDate.of(1990, 1, 1));

        Role clientRole = new Role(4L, "ROLE_CLIENT", "ROLE_CLIENT"); //
        when(rolePersistencePort.getRoleById(Constants.CLIENT_ROLE_ID)).thenReturn(clientRole);

        // Act
        userUseCase.createClient(client);

        // Assert
        verify(rolePersistencePort, times(1)).getRoleById(Constants.CLIENT_ROLE_ID);
        verify(userPersistencePort, times(1)).saveUser(client);
        verifyNoMoreInteractions(rolePersistencePort, userPersistencePort);
    }


    @Test
    void createUserWithRole_ShouldValidateAgeAndSaveUser() throws Exception {
        // Arrange
        User user = new User();
        user.setBirthdate(LocalDate.of(1990, 1, 1));
        Long roleId = 1L;

        Role role = new Role(1L, "ROLE_ADMIN", "ROLE_ADMIN");
        when(rolePersistencePort.getRoleById(roleId)).thenReturn(role);

        Method createUserWithRoleMethod = UserUseCase.class.getDeclaredMethod("createUserWithRole", User.class, Long.class);
        createUserWithRoleMethod.setAccessible(true);

        // Act
        createUserWithRoleMethod.invoke(userUseCase, user, roleId);

        // Assert
        verify(rolePersistencePort, times(1)).getRoleById(roleId);
        verify(userPersistencePort, times(1)).saveUser(user);
        verifyNoMoreInteractions(rolePersistencePort, userPersistencePort);
    }

    @Test
    void createUserWithRole_ShouldNotValidateAgeAndSaveUserWhenRoleIdIsClient() {
        // Arrange
        User user = new User();
        user.setBirthdate(LocalDate.of(1990, 1, 1));
        Long roleId = Constants.CLIENT_ROLE_ID;

        Role role = new Role(4L, "ROLE_CLIENT", "ROLE_CLIENT");
        when(rolePersistencePort.getRoleById(roleId)).thenReturn(role);

        // Act
        userUseCase.createClient(user);

        // Assert
        verify(rolePersistencePort, times(1)).getRoleById(roleId);
        verify(userPersistencePort, times(1)).saveUser(user);
        verifyNoMoreInteractions(rolePersistencePort, userPersistencePort);
    }

    @Test
    void getUserById_ShouldReturnUserFromPersistencePort() {
        // Arrange
        Long userId = 1L;
        User expectedUser = new User();
        when(userPersistencePort.getUserById(userId)).thenReturn(expectedUser);

        // Act
        User result = userUseCase.getUserById(userId);

        // Assert
        verify(userPersistencePort, times(1)).getUserById(userId);
        verifyNoMoreInteractions(userPersistencePort);
        assertEquals(expectedUser, result);
    }*/

}