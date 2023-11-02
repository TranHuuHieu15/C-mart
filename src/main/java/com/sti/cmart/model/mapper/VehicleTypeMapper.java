package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.VehicleType;
import com.sti.cmart.model.dto.VehicleTypeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class VehicleTypeMapper implements Function<VehicleType, VehicleTypeDTO> {

    @Override
    public VehicleTypeDTO apply(VehicleType vehicleType) {
        return VehicleTypeDTO.builder()
                .name(vehicleType.getName())
                .rate(vehicleType.getRate())
                .description(vehicleType.getDescription())
                .build();
    }

    public VehicleType applyToVehicleType(VehicleTypeDTO dto) {
        return VehicleType.builder()
                .name(dto.getName())
                .rate(dto.getRate())
                .description(dto.getDescription())
                .build();
    }
}
