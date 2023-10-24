package com.sti.cmart.repository;

import com.sti.cmart.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {
    Optional<Ward> findByName(String name);
}
