package com.sti.cmart.repository;

import com.sti.cmart.entity.Province;
import com.sti.cmart.entity.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    Page<Service> findByName(String name, Pageable pageable);

    @Query("select s from Service s inner join s.regionApplies regionApplies where regionApplies.province.name like ?1")
    List<Service> findAllByRegionAppliesProvinceName(String province);
}
