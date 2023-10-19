package com.sti.cmart.repository;

import com.sti.cmart.entity.Trip;
import com.sti.cmart.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
