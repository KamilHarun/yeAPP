package com.example.lightcrew.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "team_members")
public class TeamMember extends BaseEntity {

    private String name;
    private String surname;
    private String email;
    private String phone;
    private String role;
    private String bio;
    private String imageUrl;
    private String linkedin;
    private String instagram;
    private String year;


}

