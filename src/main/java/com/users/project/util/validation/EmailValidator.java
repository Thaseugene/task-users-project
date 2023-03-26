package com.users.project.util.validation;

import com.users.project.model.User;

public class EmailValidator implements Validator {

    private Validator nextValidator;

    private static final int MAX_LENGTH = 50;
    public static final String EMAIL_PATTERN = "^(?=.+)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@(\\w*).((\\.[a-z]{2,6})|(\\.[a-z]{2,6}." +
            "\\.[a-z]{2,6}))$";

    @Override
    public void validate(User user) throws ValidationException {
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new ValidationException("Email cannot be null or empty");
        } else if (user.getEmail().length() > MAX_LENGTH) {
            throw new ValidationException("Email should be no more than " + MAX_LENGTH + " characters long");
        } else if (!user.getEmail().matches(EMAIL_PATTERN)) {
            throw new ValidationException("Invalid email format");
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
