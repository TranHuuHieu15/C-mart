package com.sti.cmart.service.impl;

import com.sti.cmart.entity.VehicleType;
import com.sti.cmart.model.dto.VehicleTypeDTO;
import com.sti.cmart.model.mapper.VehicleTypeMapper;
import com.sti.cmart.repository.VehicleTypeRepository;
import com.sti.cmart.service.VehicleTypeService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleTypeServiceImpl implements VehicleTypeService {

    private final VehicleTypeRepository vehicleTypeRepository;
    private final VehicleTypeMapper vehicleTypeMapper;

    //findById
    @Override
    public VehicleTypeDTO findById(Long id) {
        Optional<VehicleType> dto = vehicleTypeRepository.findById(id);
        return dto.map(vehicleTypeMapper::apply).orElse(null);
    }

    //findAll
    @Override
    public Page<VehicleTypeDTO> findAll(SearchCriteria searchCriteria) {
        Page<VehicleType> list = vehicleTypeRepository.findAll(Search.getPageable(searchCriteria));
        return list.map(vehicleTypeMapper::apply);
    }

    //save
    @Override
    public VehicleTypeDTO save(VehicleTypeDTO vehicleTypeDTO) {
        VehicleType vehicleType = vehicleTypeMapper.applyToVehicleType(vehicleTypeDTO);
        vehicleType = vehicleTypeRepository.save(vehicleType);
        return vehicleTypeMapper.apply(vehicleType);
    }

    //delete
    @Override
    public void delete(Long id) {
        vehicleTypeRepository.deleteById(id);
    }

}
