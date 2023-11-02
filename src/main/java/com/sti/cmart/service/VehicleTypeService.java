package com.sti.cmart.service;

import com.sti.cmart.model.dto.VehicleTypeDTO;
import com.sti.cmart.util.SearchCriteria;
import org.springframework.data.domain.Page;

public interface VehicleTypeService {
    //findById
    VehicleTypeDTO findById(Long id);

    //findAll
    Page<VehicleTypeDTO> findAll(SearchCriteria searchCriteria);

    //save
    VehicleTypeDTO save(VehicleTypeDTO vehicleTypeDTO);

    //delete
    void delete(Long id);
}
