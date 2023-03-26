package com.users.project.service.impl;

import com.users.project.model.User;
import com.users.project.repository.UserRepository;
import com.users.project.repository.exception.UserRepositoryException;
import com.users.project.repository.impl.UserRepositoryImpl;
import com.users.project.service.UserService;
import com.users.project.service.exception.UserServiceException;
import com.users.project.util.validation.UserValidator;
import com.users.project.util.validation.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private final UserRepository userRepository;
    private final UserValidator validator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserValidator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public void save(User user) throws UserServiceException {
        try {
            validator.validate(user);
            userRepository.save(user);
        } catch (UserRepositoryException e) {
            throw new UserServiceException(e.getMessage(), e);
        } catch (ValidationException e) {
            LOGGER.warn("Validation problems occurred: {} - {}", e.getClass().getSimpleName(), e.getMessage());
            throw new UserServiceException(e.getMessage(), e);
        }

    }

    @Override
    public List<User> getAllUsers(int page, int size) throws UserServiceException{
        try {
            return userRepository.getAllUsers(page, size);
        } catch (UserRepositoryException e) {
            throw new UserServiceException(e.getMessage(), e);
        }

    }
}
