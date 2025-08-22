package org.example.commonms.Exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Getter
public class InvalidOldPasswordException extends RuntimeException {

    public final ErrorMessage errorMessage;
    public InvalidOldPasswordException(ErrorMessage errorMessage, Object... args) {
        super(String.format(errorMessage.getMessage(), args));
        this.errorMessage = errorMessage;
    }

}
