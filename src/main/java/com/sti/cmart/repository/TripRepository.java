package com.sti.cmart.repository;

import com.sti.cmart.entity.Service;
import com.sti.cmart.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
}
