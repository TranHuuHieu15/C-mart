package com.sti.cmart.repository;

import com.sti.cmart.entity.Customer;
import com.sti.cmart.entity.Chat;
import com.sti.cmart.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);
    Optional<Customer> findByEmail(String email);

    Page<Customer> findAll(Pageable pageable);

    Page<Customer> findAllByFullname(String fullname, Pageable pageable);

    Optional<Customer> findByPhone(String phone);
}
