package com.sti.cmart.repository;

import com.sti.cmart.entity.Province;
import com.sti.cmart.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
}
