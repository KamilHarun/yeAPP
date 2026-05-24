package com.example.lightcrew.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "contacts")
@EqualsAndHashCode(callSuper = true)

public class Contact extends BaseEntity  {

    private Long messageId;
    private String message;
    private String name;
    private String surname;
    private String email;
    private String phone;


}
