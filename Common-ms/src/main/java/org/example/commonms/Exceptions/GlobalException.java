package org.example.commonms.Exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jdk.jfr.RecordingState;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.http.HttpClient;
import java.nio.channels.AlreadyBoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalException {
    private final MessageSource messageSource;

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundExceptionRecordingState(UserNotFoundException exception , HttpServletRequest http){

        Map<String,String> error = new HashMap<>();
        error.put("error message", exception.getErrorMessage().getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .iat(LocalDateTime.now())
                .message(error)
                .path(http.getRequestURI())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> userAlreadyExistException(UserAlreadyExistException exception , HttpServletRequest http){
        Map<String,String> error = new HashMap<>();
        error.put("error message :" , exception.getErrorMessage().getMessage() );

        ErrorResponse errorResponse = ErrorResponse.builder()
                .iat(LocalDateTime.now())
                .message(error)
                .path(http.getRequestURI())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidOldPasswordException.class)
    public ResponseEntity<ErrorResponse> invalidOldPasswordException(InvalidOldPasswordException exception , HttpServletRequest http){
        Map<String ,String> error = new HashMap<>();
        error.put("error message : " , exception.getErrorMessage().getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .iat(LocalDateTime.now())
                .message(error)
                .path(http.getRequestURI())
                .build();
        return new ResponseEntity<>(errorResponse , HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponse> roleNotFoundExceptionRecordingState(RoleNotFoundException exception , HttpServletRequest http){
        Map<String,String> error = new HashMap<>();
        error.put("error message :" , exception.getErrorMessage().getMessage() );

        ErrorResponse errorResponse = ErrorResponse.builder()
                .iat(LocalDateTime.now())
                .message(error)
                .path(http.getRequestURI())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorResponse> addressNotFoundExceptionRecordingState(AddressNotFoundException exception , HttpServletRequest http){
        Map<String,String> error = new HashMap<>();
        error.put("error message :" , exception.getErrorMessage().getMessage() );

        ErrorResponse errorResponse = ErrorResponse.builder()
                .iat(LocalDateTime.now())
                .message(error)
                .statusCode(HttpStatus.NOT_FOUND.value())
                .path(http.getRequestURI())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddressOwnershipException.class)
    public  ResponseEntity<ErrorResponse> addressOwnershipException(AddressOwnershipException exception , HttpServletRequest http){
        Map<String,String> error = new HashMap<>();
        error.put("error message :" , exception.getErrorMessage().getMessage() );

        ErrorResponse errorResponse = ErrorResponse.builder()
                .iat(LocalDateTime.now())
                .message(error)
                .path(http.getRequestURI())
                .build();
    return new ResponseEntity<ErrorResponse>(errorResponse , HttpStatus.BAD_REQUEST);
    }

}
