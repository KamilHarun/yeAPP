package com.example.lightcrew.dto.request;

import com.example.lightcrew.Enum.ProjectType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectRequestDto {

    @NotBlank(message = "Name boş ola bilməz")
    private String name;

    @NotBlank(message = "Description boş ola bilməz")
    private String description;

    private boolean active;

    @NotNull(message = "Type mütləqdir")
    private ProjectType type;

    private String imageUrl;
    private String youtubeUrl;
    private String category;
    private String caseDescription;

    @NotNull(message = "Tarix mütləqdir")
    private LocalDate projectDate;

    private int orderIndex;
    private List<String> equipment;
    private List<String> gallery;
}

