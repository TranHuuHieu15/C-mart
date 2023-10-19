package com.sti.cmart.repository;

import com.sti.cmart.entity.Vehicle;
import com.sti.cmart.entity.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {
}
