package com.arnab.ecommerceapp.repository;

import com.arnab.ecommerceapp.entity.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactDetailsRepository extends JpaRepository<ContactDetails,Long> {
}
