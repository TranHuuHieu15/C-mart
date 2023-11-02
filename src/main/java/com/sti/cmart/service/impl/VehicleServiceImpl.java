package com.sti.cmart.service.impl;

import com.sti.cmart.entity.Vehicle;
import com.sti.cmart.model.dto.VehicleDTO;
import com.sti.cmart.model.mapper.VehicleMapper;
import com.sti.cmart.repository.VehicleRepository;
import com.sti.cmart.service.VehicleService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    //findById
    @Override
    public VehicleDTO findById(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        return vehicle.map(vehicleMapper::apply).orElse(null);
    }

    //findAll
    @Override
    public Page<VehicleDTO> findAll(SearchCriteria searchCriteria) {
        Page<Vehicle> list = vehicleRepository.findAll(Search.getPageable(searchCriteria));
        return list.map(vehicleMapper::apply);
    }

    //save
    @Override
    public VehicleDTO save(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleMapper.applyToVehicle(vehicleDTO);
        return vehicleMapper.apply(vehicleRepository.save(vehicle));
    }

    //delete
    @Override
    public void delete(Long id) {
        vehicleRepository.deleteById(id);
    }

}
