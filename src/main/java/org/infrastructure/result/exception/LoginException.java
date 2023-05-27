package org.infrastructure.result.exception;

import javax.validation.ValidationException;

public class LoginException extends ValidationException {

    public LoginException(String message) {
        super(message);
    }

}
