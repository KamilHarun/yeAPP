package org.example.commonms.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    USER_NOT_FOUND_EXCEPTION ("User not found"),
    USER_NOT_FOUND_BY_ID("User not found with id: %s"),
    USER_NOT_FOUND_BY_EMAIL("User not found with email: %s"),
    USER_ALREADY_EXISTS_EXCEPTION ("User already exists"),
    USER_ALREADY_EXISTS_BY_ID("User already exists with id: %s"),
    USER_ALREADY_EXISTS_BY_EMAIL("User already exists with email: %s"),
    INVALID_OLD_PASSWORD_EXCEPTION ("Invalid old password"),
    ROLE_NOT_FOUND_EXCEPTION ("Role not found"),
    ROLE_NOT_FOUND_BY_ID("Role not found with id: %s"),
    ROLE_NOT_FOUND_BY_EMAIL("Role not found with email: %s"),
    ROLE_ALREADY_EXISTS_EXCEPTION ("Role already exists"),
    ADDRESS_NOT_FOUND_EXCEPTION ("Address not found"),
    ADDRESS_NOT_FOUND_BY_ID("Address not found with id: %s"),
    ADDRESS_NOT_FOUND_BELONG_TO_USER ("Address not found or not belongs to user: %s");


    private final String message;
    public String format(Object... args) {
        return String.format(this.message, args);
    }
}

