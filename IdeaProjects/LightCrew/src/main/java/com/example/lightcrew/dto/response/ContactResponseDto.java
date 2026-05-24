package com.example.lightcrew.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactResponseDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String message;
    private boolean isRead;
    private LocalDateTime createdAt;
}
