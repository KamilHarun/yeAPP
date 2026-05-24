package com.example.lightcrew.Model;

import com.example.lightcrew.Enum.ProjectType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "projects")
public class Project extends BaseEntity {

    private String name;
    private String description;
    private boolean active;
    @Enumerated(EnumType.STRING)
    private ProjectType type; // FILM, COMMERCIAL, CLIP

    private String imageUrl;

    private LocalDate projectDate;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private String category;
    private String youtubeUrl;
    private int orderIndex;

    @Column(columnDefinition = "TEXT")
    private String caseDescription;

    @ElementCollection
    @CollectionTable(name = "project_equipment",
            joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "equipment_item")
    private List<String> equipment;

    @ElementCollection
    @CollectionTable(name = "project_gallery",
            joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "gallery_url")
    private List<String> gallery;
}
