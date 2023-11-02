package com.sti.cmart.service;

import com.sti.cmart.model.dto.VehicleDTO;
import com.sti.cmart.util.SearchCriteria;
import org.springframework.data.domain.Page;

public interface VehicleService {
    //findById
    VehicleDTO findById(Long id);

    //findAll
    Page<VehicleDTO> findAll(SearchCriteria searchCriteria);

    //save
    VehicleDTO save(VehicleDTO vehicleDTO);

    //delete
    void delete(Long id);
}
