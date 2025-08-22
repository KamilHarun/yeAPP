package org.example.commonms.Exceptions;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

    public final ErrorMessage errorMessage;

    public UserNotFoundException(ErrorMessage message , Object... args) {
        super(String.format(message.getMessage(), args));
        this.errorMessage = message;
    }
}
