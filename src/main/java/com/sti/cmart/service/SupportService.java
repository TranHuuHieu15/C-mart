package com.sti.cmart.service;

import com.sti.cmart.model.dto.SupportDTO;
import com.sti.cmart.util.SearchCriteria;
import org.springframework.data.domain.Page;

public interface SupportService {
    //findById
    SupportDTO findById(Long id);

    //findAll
    Page<SupportDTO> findAll(SearchCriteria searchCriteria);

    //save
    SupportDTO save(SupportDTO supportDTO);

    //delete
    void delete(Long id);

    //findByName
    SupportDTO findByName(String name);

}
