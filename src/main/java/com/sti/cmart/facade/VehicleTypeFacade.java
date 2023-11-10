package com.sti.cmart.facade;

import com.sti.cmart.exception.common.InvalidParamException;
import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.exception.entity.EntityAlreadyExistException;
import com.sti.cmart.exception.entity.EntityNotFoundException;
import com.sti.cmart.model.dto.VehicleDTO;
import com.sti.cmart.model.dto.VehicleTypeDTO;
import com.sti.cmart.service.VehicleTypeService;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleTypeFacade {

    private final VehicleTypeService vehicleTypeService;

    //findById
    public VehicleTypeDTO findById(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        VehicleTypeDTO dto = vehicleTypeService.findById(id);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //findAll
    public Page<VehicleTypeDTO> findAll(SearchCriteria searchCriteria) throws ArchitectureException {
        Page<VehicleTypeDTO> list = vehicleTypeService.findAll(searchCriteria);
        if (list.isEmpty())
            throw new EntityNotFoundException();
        return list;
    }

    //save
    public VehicleTypeDTO save(VehicleTypeDTO vehicleTypeDTO) throws ArchitectureException {
        if (vehicleTypeDTO == null)
            throw new InvalidParamException();
//        VehicleTypeDTO dto = vehicleTypeService.findById(vehicleTypeDTO.getId());
//        if (dto != null)
//            throw new EntityAlreadyExistException();
        return vehicleTypeService.save(vehicleTypeDTO);
    }

    //update
    public VehicleTypeDTO update(Long id, VehicleTypeDTO dto) throws ArchitectureException {
        if (id == null || dto == null)
            throw new InvalidParamException();
        findById(id);
        VehicleTypeDTO vehicleTypeDTO = vehicleTypeService.findById(id);
        if (vehicleTypeDTO == null)
            throw new EntityNotFoundException();
        vehicleTypeDTO.setId(dto.getId());
        vehicleTypeDTO.setName(dto.getName());
        vehicleTypeDTO.setDescription(dto.getDescription());
        vehicleTypeDTO.setRate(dto.getRate());
        return vehicleTypeService.save(vehicleTypeDTO);
    }

    //delete
    public void delete(Long id) throws ArchitectureException {
        findById(id);
        vehicleTypeService.delete(id);
    }

}
