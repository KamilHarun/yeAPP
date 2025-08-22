package org.example.commonms.Exceptions;

import lombok.Getter;

@Getter
public class RoleNotFoundException extends RuntimeException{

    public final ErrorMessage errorMessage;

    public RoleNotFoundException(ErrorMessage errorMessage , Object... args) {
        super(String.format(errorMessage.getMessage(), args));
        this.errorMessage = errorMessage;
    }
}
