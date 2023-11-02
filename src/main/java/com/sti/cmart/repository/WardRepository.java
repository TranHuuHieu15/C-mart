package com.sti.cmart.repository;

import com.sti.cmart.entity.District;
import com.sti.cmart.entity.Ward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {
    Page<Ward> findByName(String name, Pageable pageable);
}
