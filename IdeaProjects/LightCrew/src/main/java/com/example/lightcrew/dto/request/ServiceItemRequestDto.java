package com.example.lightcrew.dto.request;

import com.example.lightcrew.Model.ServiceGalleryImage;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceItemRequestDto {

    @NotBlank(message = "Num boş ola bilməz")
    private String num;

    @NotBlank(message = "Title boş ola bilməz")
    private String title;

    @NotBlank(message = "Description boş ola bilməz")
    private String description;

    private String iconClass;
    private String cardStyle;
    private int orderIndex;
    private boolean active;
    private List<String> tags;
    private List<ServiceGalleryImage> gallery;
}
