package com.users.project.util.validation;

import com.users.project.model.User;

public class NameValidator implements Validator {

    private Validator nextValidator;
    private static final int MAX_LENGTH = 20;

    @Override
    public void validate(User user) throws ValidationException {
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new ValidationException("Name cannot be null or empty");
        } else if (!user.getName().matches("[A-Za-z]+")) {
            throw new ValidationException("Name should contain only Latin letters");
        } else if (user.getName().length() > MAX_LENGTH) {
            throw new ValidationException("Name should be no more than " + MAX_LENGTH + " characters long");
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
