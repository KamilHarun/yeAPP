package com.example.lightcrew.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamMembersRequestDto {
    @NotBlank(message = "Ad boş ola bilməz")
    private String name;

    private String surname;

    @NotBlank(message = "Rol boş ola bilməz")
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
}
