package org.example.userms.Model;

import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.io.Serializable;
import java.util.Set;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Roles implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name; // e.g., ROLE_CUSTOMER, ROLE_OWNER, ROLE_DELIVERY, ROLE_ADMIN

    @Column(unique = true , nullable = false)
    private String description;



}
