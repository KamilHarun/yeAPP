package org.example.userms.Dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {

    @NotBlank(message = "First name is required")
    private String username;

    @NotBlank(message = "Last name is required")
    private String surname;

    @Min(value = 1, message = "Age must be positive")
    private Long age;

    @Email(message = "Email should be valid")
    @NotBlank
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Invalid phone number format")
    private String phoneNumber;

    @NotBlank(message = "City name is required")
    private String city;

    @NotBlank(message = "Street name is required")
    private String street;
}
