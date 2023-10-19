package com.sti.cmart.repository;

import com.sti.cmart.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
    Optional<Admin> findByEmail(String email);

    Page<Admin> findAll(Pageable pageable);

    Page<Admin> findAllByFullname(String fullname, Pageable pageable);

    Optional<Admin> findByPhone(String phone);
}
