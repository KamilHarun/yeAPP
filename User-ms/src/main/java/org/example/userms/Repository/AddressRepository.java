package org.example.userms.Repository;

import org.example.userms.Model.Address;
import org.example.userms.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.tags.form.SelectTag;

import java.util.List;
import java.util.Optional;

import static javax.swing.text.html.HTML.Tag.SELECT;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUserId(Long userId);

    void clearPrimaryForUser(Long userId);

    Optional<Object> findByIdAndUserId(Long addressId, Long userId);

}
