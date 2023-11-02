package com.sti.cmart.facade;

import com.sti.cmart.entity.Vehicle;
import com.sti.cmart.exception.common.InvalidParamException;
import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.exception.entity.EntityAlreadyExistException;
import com.sti.cmart.exception.entity.EntityNotFoundException;
import com.sti.cmart.model.dto.VehicleDTO;
import com.sti.cmart.service.VehicleService;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleFacade {

    private final VehicleService vehicleService;

    //findById
    public VehicleDTO findById(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        VehicleDTO vehicle = vehicleService.findById(id);
        if (vehicle == null)
            throw new EntityNotFoundException();
        return vehicle;
    }

    //findAll
    public Page<VehicleDTO> findAll(SearchCriteria searchCriteria) throws ArchitectureException {
        Page<VehicleDTO> list = vehicleService.findAll(searchCriteria);
        if (list.isEmpty())
            throw new EntityNotFoundException();
        return list;
    }

    //save
    public VehicleDTO save(VehicleDTO vehicleDTO) throws ArchitectureException {
        if (vehicleDTO == null)
            throw new InvalidParamException();
        VehicleDTO dto = vehicleService.findById(vehicleDTO.getId());
        if (dto != null)
            throw new EntityAlreadyExistException();
        return vehicleService.save(vehicleDTO);
    }

    //update
    public VehicleDTO update (Long id, VehicleDTO vehicleDTO) throws ArchitectureException {
        if (id == null || vehicleDTO == null)
            throw new InvalidParamException();
        VehicleDTO dto = vehicleService.findById(id);
        if (dto == null)
            throw new EntityNotFoundException();
        dto.setBrand(vehicleDTO.getBrand());
        dto.setName(vehicleDTO.getName());
        dto.setColor(vehicleDTO.getColor());
        dto.setDescription(vehicleDTO.getDescription());
        dto.setVehicleTypeId(vehicleDTO.getVehicleTypeId());
        dto.setAccountId(vehicleDTO.getAccountId());
        dto.setLicensePlate(vehicleDTO.getLicensePlate());
        dto.setYearOfManufacture(vehicleDTO.getYearOfManufacture());
        return vehicleService.save(dto);
    }

    //delete
    public void delete(Long id) throws ArchitectureException {
        findById(id);
        vehicleService.delete(id);
    }

}
