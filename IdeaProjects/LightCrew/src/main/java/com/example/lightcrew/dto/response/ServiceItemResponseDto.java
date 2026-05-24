package com.example.lightcrew.dto.response;

import com.example.lightcrew.Model.ServiceGalleryImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceItemResponseDto {

    private Long id;
    private String num;
    private String title;
    private String description;
    private String iconClass;
    private String cardStyle;
    private int orderIndex;
    private boolean active;
    private List<String> tags;
    private List<ServiceGalleryImage> gallery;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}





