package com.users.project.service.impl;

import com.users.project.model.User;
import com.users.project.model.UserRole;
import com.users.project.repository.UserRepository;
import com.users.project.repository.exception.UserRepositoryException;
import com.users.project.service.UserService;
import com.users.project.util.validation.UserValidator;
import com.users.project.util.validation.ValidationException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private UserValidator validator;

    public UserServiceImplTest() {
    }

    @Test
    public void testSaveValidUser() throws Exception {
        User user = new User("John", "Walker", "William", "john.walker@gmail.com", UserRole.ADMINISTRATOR);
        userService.save(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testValidUser() {
        User user = new User("John", "Walker", "William", "john.walker@gmail.com", UserRole.ADMINISTRATOR);
        assertDoesNotThrow(() -> validator.validate(user));
    }

    @Test(expected = ValidationException.class)
    public void testInvalidSurname() throws ValidationException {
        User user = new User("John", "", null, "john.walker@gmail.com", UserRole.ADMINISTRATOR);
        validator.validate(user);
    }

    @Test
    public void testGetUsers() throws UserRepositoryException {
        int page = 1;
        int size = 10;
        List<User> usersList = new ArrayList<>();

        for (int i = 0; i <= 9; i++) {
            usersList.add(new User("John", "Walker", "William", "john.walker@gmail.com", UserRole.ADMINISTRATOR));
        }
        when(userRepository.getAllUsers(page, size)).thenReturn(usersList);
        List<User> actualUsersList = userRepository.getAllUsers(page, size);

        Assertions.assertEquals(usersList, actualUsersList);

    }

}