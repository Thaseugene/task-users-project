package com.users.project.util.validation;

import com.users.project.model.User;

public class SurnameValidator implements Validator {

    private Validator nextValidator;
    private static final int MAX_LENGTH = 40;

    @Override
    public void validate(User user) throws ValidationException {
        if (user.getSurname() == null || user.getSurname().trim().isEmpty()) {
            throw new ValidationException("Surname cannot be null or empty");
        } else if (!user.getSurname().matches("[A-Za-z]+")) {
            throw new ValidationException("Surname should contain only Latin letters");
        } else if (user.getSurname().length() > MAX_LENGTH) {
            throw new ValidationException("Surname should be no more than " + MAX_LENGTH + " characters long");
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
