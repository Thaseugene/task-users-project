package com.users.project.util.validation;

import com.users.project.model.User;

public class UserRoleValidator implements Validator {

    private Validator nextValidator;

    @Override
    public void validate(User user) throws ValidationException {
        if (user.getRole() == null) {
            throw new ValidationException("User role cannot be null");
        } else if (nextValidator != null) {
            nextValidator.validate(user);
        }
    }

    @Override
    public Validator setNext(Validator validator) {
        this.nextValidator = validator;
        return validator;
    }
}
