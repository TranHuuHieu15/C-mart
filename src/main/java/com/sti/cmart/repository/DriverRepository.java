package com.sti.cmart.repository;

import com.sti.cmart.entity.Customer;
import com.sti.cmart.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
