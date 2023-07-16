package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.MailAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UserAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserMysqlAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void saveUser(User user) {
        if (!isUpdatingData(user)) {
            if (userRepository.findByDniNumber(user.getDniNumber()).isPresent()) {
                throw new UserAlreadyExistsException();
            }

            if (userRepository.existsByMail(user.getMail())){
                throw new MailAlreadyExistsException();
            }
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity savedUserEntity = userRepository.save(userEntityMapper.toEntity(user));
        User savedUser = userEntityMapper.userEntityToUser(savedUserEntity);
        savedUser.setId(savedUserEntity.getId());
        user.setId(savedUser.getId());
    }

    private boolean isUpdatingData(User user) {
        return user.getId() != null;
    }
    @Override
    public User getUserById(Long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        UserEntity userEntity = userEntityOptional.orElseThrow(NoDataFoundException::new);
        return userEntityMapper.userEntityToUser(userEntity);
    }

    @Override
    public User deleteUser(User user) {
        userRepository.deleteById(user.getId());
        return user;
    }

    @Override
    public List<User> getUsers(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<UserEntity> userPage = userRepository.findAll(pageable);
        List<UserEntity> userEntities = userPage.getContent();
        return userEntities.stream()
                .map(userEntityMapper::userEntityToUser)
                .collect(Collectors.toList());
    }
}
