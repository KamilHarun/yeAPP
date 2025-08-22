package org.example.commonms.Exceptions;

import lombok.Getter;

@Getter
public class AddressNotFoundException extends RuntimeException{

    public final ErrorMessage errorMessage;

    public AddressNotFoundException(ErrorMessage errorMessage , Object... args) {
        super(String.format(errorMessage.getMessage(), args));
        this.errorMessage = errorMessage;
    }

}
