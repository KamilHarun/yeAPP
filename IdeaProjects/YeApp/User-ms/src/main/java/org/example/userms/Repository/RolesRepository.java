package org.example.userms.Repository;

import org.example.userms.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {
    Optional<Object> findByNameIgnoreCase(String name);

    Optional<Roles> findByName(String name);

}
