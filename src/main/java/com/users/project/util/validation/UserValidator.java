package com.users.project.util.validation;

import com.users.project.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    private final Validator chain;

    public UserValidator() {
        // Chain the validators together so that each validator only handles the validation of a specific field
        this.chain = new SurnameValidator();
        chain.setNext(new NameValidator())
                .setNext(new MiddleNameValidator())
                .setNext(new EmailValidator())
                .setNext(new UserRoleValidator());
    }

    public void validate(User user) throws ValidationException {
        chain.validate(user); // Start the validation chain
    }
}

