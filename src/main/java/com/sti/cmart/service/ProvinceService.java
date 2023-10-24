package com.sti.cmart.service;

import com.sti.cmart.model.dto.ProvinceDTO;
import com.sti.cmart.util.SearchCriteria;
import org.springframework.data.domain.Page;

public interface ProvinceService {
    //findById
    ProvinceDTO findById(Long id);

    //findAll
    Page<ProvinceDTO> findAll(SearchCriteria searchCriteria);

    //findByName
    Page<ProvinceDTO> findByName(String name, SearchCriteria searchCriteria);

    //save
    ProvinceDTO save(ProvinceDTO provinceDTO);

    //delete
    void delete(Long id);
}
