package com.sti.cmart.service;

import com.sti.cmart.model.dto.ServiceDTO;
import com.sti.cmart.util.SearchCriteria;
import org.springframework.data.domain.Page;

public interface ServicesService {
    //findById
    ServiceDTO findById(Long id);

    //findByName
    Page<ServiceDTO> findByName(String name, SearchCriteria searchCriteria);

    //findAll
    Page<ServiceDTO> findAll(SearchCriteria searchCriteria);

    //save
    ServiceDTO save(ServiceDTO serviceDTO);

    //delete
    void delete(Long id);
}
