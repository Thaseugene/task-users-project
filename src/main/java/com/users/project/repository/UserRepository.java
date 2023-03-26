package com.users.project.repository;

import com.users.project.model.User;
import com.users.project.repository.exception.UserRepositoryException;

import java.util.List;

public interface UserRepository {

    void save(User user) throws UserRepositoryException;

    List<User> getAllUsers(int page, int size) throws UserRepositoryException;

}
