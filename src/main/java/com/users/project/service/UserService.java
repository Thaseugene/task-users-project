package com.users.project.service;

import com.users.project.model.User;
import com.users.project.service.exception.UserServiceException;

import java.util.List;

public interface UserService {

    void save(User user) throws UserServiceException;
    List<User> getAllUsers(int page, int size) throws UserServiceException;

}
