package com.example.menums.Dto.Request;

import com.example.menums.enums.CategoryType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemCreateDto {
    @NotNull(message = "Restaurant ID must not be null")
    @Positive(message = "Restaurant ID must be a positive number")
    private Long restaurantId;

    @NotBlank(message = "Name must not be blank")
    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;

    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

    @NotNull(message = "Price must not be null")
    @PositiveOrZero(message = "Price must be zero or a positive number")
    private Long price;

    private boolean isAvailable;

    @NotNull(message = "Category type must not be null")
    private CategoryType categoryType;

    @PastOrPresent(message = "Created at must be in the past or present")
    private LocalDateTime createdAt;

    @PastOrPresent(message = "Updated at must be in the past or present")
    private LocalDateTime updatedAt;
}

