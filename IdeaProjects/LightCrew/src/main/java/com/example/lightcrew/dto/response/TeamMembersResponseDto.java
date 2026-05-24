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
public class TeamMembersResponseDto {

    private Long id;
    private String name;
    private String surname;
    private String role;
    private String years;
    private String bio;
    private String imageUrl;
    private String email;
    private String phone;
    private String linkedin;
    private String instagram;
    private int orderIndex;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
