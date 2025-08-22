package org.example.userms.Repository;

import org.example.userms.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.username = :username AND u.surname = :surname")
    Optional<User> getMyProfile(@Param("username") String firstName, @Param("surname") String surname);

    Optional<User> findByEmail(@Param("email") String email);

    Optional<User> findByUsername(String  username);
}
