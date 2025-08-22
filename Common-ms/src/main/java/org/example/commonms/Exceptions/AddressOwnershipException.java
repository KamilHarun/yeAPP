package org.example.commonms.Exceptions;

import lombok.Getter;

@Getter
public class AddressOwnershipException extends RuntimeException {

    public final ErrorMessage errorMessage;

    public AddressOwnershipException(ErrorMessage errorMessage, Object... args) {
        super(String.format(errorMessage.getMessage(), args));
        this.errorMessage = errorMessage;

    }
}
