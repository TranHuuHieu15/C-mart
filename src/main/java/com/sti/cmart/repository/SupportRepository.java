package com.sti.cmart.repository;

import com.sti.cmart.entity.Service;
import com.sti.cmart.entity.Support;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupportRepository extends JpaRepository<Support, Long> {
    Optional<Support> findByName(String name);
    Optional<Support> findByPhone(String phone);
}
