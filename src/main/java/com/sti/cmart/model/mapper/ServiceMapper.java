package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.Service;
import com.sti.cmart.model.dto.ServiceDTO;
import com.sti.cmart.repository.VehicleTypeRepository;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceMapper implements Function<Service, ServiceDTO> {

    private final VehicleTypeRepository vehicleTypeRepository;

    @Override
    public ServiceDTO apply(Service service) {
        return ServiceDTO.builder()
                .id(service.getId())
                .name(service.getName())
                .poster(service.getPoster())
                .description(service.getDescription())
                .minKmRequire(service.getMinKmRequire())
                .pricePerKm(service.getPricePerKm())
                .minPrice(service.getMinPrice())
                .vehicleTypeId(service.getVehicleType().getId())
                .build();
    }

    public Service applyToService(ServiceDTO serviceDTO) {
        return Service.builder()
                .id(serviceDTO.getId())
                .name(serviceDTO.getName())
                .poster(serviceDTO.getPoster())
                .description(serviceDTO.getDescription())
                .minKmRequire(serviceDTO.getMinKmRequire())
                .pricePerKm(serviceDTO.getPricePerKm())
                .minPrice(serviceDTO.getMinPrice())
                .vehicleType(vehicleTypeRepository.findById(serviceDTO.getVehicleTypeId()).get())
                .build();
    }

}
