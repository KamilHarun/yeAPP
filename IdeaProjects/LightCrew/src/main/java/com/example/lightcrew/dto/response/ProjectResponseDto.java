package com.example.lightcrew.dto.response;

import com.example.lightcrew.Enum.ProjectType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectResponseDto {
    private long id;
    private String name;
    private String description;
    private boolean active;
    private ProjectType type;
    private String imageUrl;
    private String youtubeUrl;
    private String category;
    private String caseDescription;
    private LocalDate projectDate;
    private int orderIndex;
    private List<String> equipment;
    private List<String> gallery;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
