package com.users.project.util.validation;

import com.users.project.model.User;

public class MiddleNameValidator implements Validator {

    private Validator nextValidator;
    private static final int MAX_LENGTH = 40;

    @Override
    public void validate(User user) throws ValidationException {
        if (user.getMiddleName() == null || user.getMiddleName().trim().isEmpty()) {
            throw new ValidationException("Middle name cannot be null or empty");
        } else if (!user.getMiddleName().matches("[A-Za-z]+")) {
            throw new ValidationException("Middle name should contain only Latin letters");
        } else if (user.getMiddleName().length() > MAX_LENGTH) {
            throw new ValidationException("Middle name should be no more than " + MAX_LENGTH + " characters long");
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
