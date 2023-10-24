package com.sti.cmart.repository;

import com.sti.cmart.entity.Chat;
import com.sti.cmart.entity.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    Page<Province> findByName(String name, Pageable pageable);
}
