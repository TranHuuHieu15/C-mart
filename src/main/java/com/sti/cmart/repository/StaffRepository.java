package com.sti.cmart.repository;

import com.sti.cmart.entity.Staff;
import com.sti.cmart.entity.Service;
import com.sti.cmart.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByUsername(String username);
    Optional<Staff> findByEmail(String email);

    Page<Staff> findAll(Pageable pageable);

    Page<Staff> findAllByFullname(String fullname, Pageable pageable);

    Optional<Staff> findByPhone(String phone);
}
