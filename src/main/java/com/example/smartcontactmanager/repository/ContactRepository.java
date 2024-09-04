package com.example.smartcontactmanager.repository;

import com.example.smartcontactmanager.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
