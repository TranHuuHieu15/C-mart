package com.sti.cmart.service;

import com.google.maps.errors.ApiException;
import com.sti.cmart.model.dto.ServiceDTO;
import com.sti.cmart.other.request.DistanceRequest;
import com.sti.cmart.util.SearchCriteria;
import org.springframework.data.domain.Page;

import java.io.IOException;

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

    Object findByCityByAddress(DistanceRequest distanceRequest) throws IOException, InterruptedException, ApiException;
}
