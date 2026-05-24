package com.example.lightcrew.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "service_items")
@EqualsAndHashCode(callSuper = true)
public class ServiceItem extends BaseEntity {

    private String num;        // "01", "02"
    private String title;
    private String iconClass;  // "fas fa-film"
    private String cardStyle;  // "normal", "dark", "accent"
    private int orderIndex;
    private boolean active;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    @CollectionTable(name = "service_tags",
            joinColumns = @JoinColumn(name = "service_id"))
    @Column(name = "tag")
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "service_gallery",
            joinColumns = @JoinColumn(name = "service_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "src",     column = @Column(name = "image_src")),
            @AttributeOverride(name = "caption", column = @Column(name = "image_caption"))
    })
    private List<ServiceGalleryImage> gallery;


}

