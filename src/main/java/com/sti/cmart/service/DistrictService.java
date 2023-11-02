package com.sti.cmart.service;

import com.sti.cmart.model.dto.DistrictDTO;
import com.sti.cmart.util.SearchCriteria;
import org.springframework.data.domain.Page;

public interface DistrictService {
    //findById
    DistrictDTO findById(Long id);

    //findAll
    Page<DistrictDTO> findAll(SearchCriteria searchCriteria);

    //findByName
    Page<DistrictDTO> findByName(String name, SearchCriteria searchCriteria);

    //save
    DistrictDTO save(DistrictDTO districtDTO);

    //delete
    void delete(Long id);

    DistrictDTO update(Long id, DistrictDTO dto);
}
