package com.sti.cmart.service;

import com.sti.cmart.model.dto.TripDTO;
import com.sti.cmart.util.SearchCriteria;
import org.springframework.data.domain.Page;

public interface TripService {
    //findById
    TripDTO findById(Long id);

    //findAll
    Page<TripDTO> findAll(SearchCriteria searchCriteria);

    //save
    TripDTO save(TripDTO tripDTO);

    //delete
    void delete(Long id);
}
