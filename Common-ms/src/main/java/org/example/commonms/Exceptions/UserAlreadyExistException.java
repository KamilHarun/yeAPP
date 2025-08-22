package org.example.commonms.Exceptions;

import lombok.Getter;

@Getter
public class UserAlreadyExistException extends RuntimeException {

    public final  ErrorMessage errorMessage;

    public UserAlreadyExistException(ErrorMessage errorMessage, Object... args) {
        super(String.format(errorMessage.getMessage(), args));
        this.errorMessage = errorMessage;
    }
}
