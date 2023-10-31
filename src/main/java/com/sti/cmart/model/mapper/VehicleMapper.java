package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.Vehicle;
import com.sti.cmart.model.dto.VehicleDTO;
import com.sti.cmart.repository.AccountRepository;
import com.sti.cmart.repository.VehicleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class VehicleMapper implements Function<Vehicle, VehicleDTO> {

    private final AccountRepository accountRepository;
    private final VehicleTypeRepository vehicleTypeRepository;

    @Override
    public VehicleDTO apply(Vehicle vehicle) {
        return VehicleDTO.builder()
                .id(vehicle.getId())
                .name(vehicle.getName())
                .licensePlate(vehicle.getLicensePlate())
                .color(vehicle.getColor())
                .brand(vehicle.getBrand())
                .yearOfManufacture(vehicle.getYearOfManufacture())
                .description(vehicle.getDescription())
                .vehicleTypeId(vehicle.getVehicleType().getId())
                .accountId(vehicle.getAccount().getId())
                .build();
    }

    public Vehicle applyToVehicle(VehicleDTO vehicleDTO) {
        return Vehicle.builder()
                .id(vehicleDTO.getId())
                .name(vehicleDTO.getName())
                .licensePlate(vehicleDTO.getLicensePlate())
                .color(vehicleDTO.getColor())
                .brand(vehicleDTO.getBrand())
                .yearOfManufacture(vehicleDTO.getYearOfManufacture())
                .description(vehicleDTO.getDescription())
                .vehicleType(vehicleTypeRepository.findById(vehicleDTO.getVehicleTypeId()).orElse(null))
                .account(accountRepository.findById(vehicleDTO.getAccountId()).orElse(null))
                .build();
    }
}
