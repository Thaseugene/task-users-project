package com.users.project.controller;

import com.users.project.model.User;
import com.users.project.service.UserService;
import com.users.project.service.exception.UserServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LogManager.getRootLogger();
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user) throws UserServiceException {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<User> getAllUsers(@RequestParam(name = "page", defaultValue = "1") int page,
                                  @RequestParam(name = "size", defaultValue = "10") int size) throws UserServiceException {
        return userService.getAllUsers(page, size);
    }

    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<String> handleServiceException(UserServiceException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ServiceException occurred: " + ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleMessageNotReadException(HttpMessageNotReadableException ex) {
        LOGGER.warn("Problems with request data: {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect request format "+ ex.getMessage());
    }
}
