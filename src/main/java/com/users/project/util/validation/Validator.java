package com.users.project.util.validation;

import com.users.project.model.User;

public interface Validator {
    void validate(User user) throws ValidationException;
    public Validator setNext(Validator validator);
}
