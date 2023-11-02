package com.sti.cmart.service;

import com.sti.cmart.model.dto.WardDTO;
import com.sti.cmart.util.SearchCriteria;
import org.springframework.data.domain.Page;

public interface WardService {
    //findById
    WardDTO findById(Long id);

    //findAll
    Page<WardDTO> findAll(SearchCriteria searchCriteria);

    //findByName
    Page<WardDTO> findByName(String name, SearchCriteria searchCriteria);

    //save
    WardDTO save(WardDTO wardDTO);

    //delete
    void delete(Long id);

    WardDTO updateById(Long id, WardDTO wardDTO);
}
