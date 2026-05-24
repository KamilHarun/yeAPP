package com.example.lightcrew.Repositories;

import com.example.lightcrew.Model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ContactMessageRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByIsReadFalse();

    List<Contact> findByIsReadFalseOrderByCreatedAtDesc();

    List<Contact> findAllByOrderByCreatedAtDesc();

}
