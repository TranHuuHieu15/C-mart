package com.sti.cmart.facade;

import com.sti.cmart.exception.common.InvalidParamException;
import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.exception.entity.EntityAlreadyExistException;
import com.sti.cmart.exception.entity.EntityNotFoundException;
import com.sti.cmart.model.dto.ServiceDTO;
import com.sti.cmart.service.ServicesService;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceFacade {

    private final ServicesService servicesService;

    //findById
    public ServiceDTO findById(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        ServiceDTO serviceDTO = servicesService.findById(id);
        if (serviceDTO == null)
            throw new EntityNotFoundException();
        return serviceDTO;
    }

    //findByName
    public Page<ServiceDTO> findByName(String name, SearchCriteria searchCriteria) throws ArchitectureException {
        if (name == null)
            throw new InvalidParamException();
        Page<ServiceDTO> serviceDTO = servicesService.findByName(name, searchCriteria);
        if (serviceDTO.isEmpty())
            throw new EntityNotFoundException();
        return serviceDTO;
    }

    //findAll
    public Page<ServiceDTO> findAll(SearchCriteria searchCriteria) throws ArchitectureException {
        Page<ServiceDTO> list = servicesService.findAll(searchCriteria);
        if (list.isEmpty())
            throw new EntityNotFoundException();
        return list;
    }

    //save
    public ServiceDTO save(ServiceDTO serviceDTO) throws ArchitectureException {
        if (serviceDTO == null)
            throw new InvalidParamException();
        ServiceDTO dto = servicesService.findById(serviceDTO.getId());
        if (dto != null)
            throw new EntityAlreadyExistException();
        return servicesService.save(serviceDTO);
    }

    //update
    public ServiceDTO update(Long id, ServiceDTO serviceDTO) throws ArchitectureException {
        if (serviceDTO == null || id == null)
            throw new InvalidParamException();
        ServiceDTO dto = servicesService.findById(id);
        if (dto == null)
            throw new EntityNotFoundException();
        dto.setId(serviceDTO.getId());
        dto.setName(serviceDTO.getName());
        dto.setPoster(serviceDTO.getPoster());
        dto.setDescription(serviceDTO.getDescription());
        dto.setMinPrice(serviceDTO.getMinPrice());
        dto.setMinKmRequire(serviceDTO.getMinKmRequire());
        dto.setPricePerKm(serviceDTO.getPricePerKm());
        dto.setVehicleTypeId(serviceDTO.getVehicleTypeId());
        return servicesService.save(dto);
    }

    //delete
    public void delete(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        findById(id);
        servicesService.delete(id);
    }

}
