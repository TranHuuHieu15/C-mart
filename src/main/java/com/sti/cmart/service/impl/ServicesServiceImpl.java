package com.sti.cmart.service.impl;

import com.sti.cmart.entity.Service;
import com.sti.cmart.model.dto.ServiceDTO;
import com.sti.cmart.model.mapper.ServiceMapper;
import com.sti.cmart.repository.ServiceRepository;
import com.sti.cmart.service.ServicesService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.Optional;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    //findById
    @Override
    public ServiceDTO findById(Long id) {
        Optional<Service> service = serviceRepository.findById(id);
        return service.map(serviceMapper::apply).orElse(null);
    }

    //findByName
    @Override
    public Page<ServiceDTO> findByName(String name, SearchCriteria searchCriteria) {
        Page<Service> service = serviceRepository.findByName(name, Search.getPageable(searchCriteria));
        return service.map(serviceMapper::apply);
    }

    //findAll
    @Override
    public Page<ServiceDTO> findAll(SearchCriteria searchCriteria) {
        Page<Service> list = serviceRepository.findAll(Search.getPageable(searchCriteria));
        return list.map(serviceMapper::apply);
    }

    //save
    @Override
    public ServiceDTO save(ServiceDTO serviceDTO) {
        Service service = serviceMapper.applyToService(serviceDTO);
        service = serviceRepository.save(service);
        return serviceMapper.apply(service);
    }

    //delete
    @Override
    public void delete(Long id) {
        serviceRepository.deleteById(id);
    }

}
