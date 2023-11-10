package com.sti.cmart.service.impl;

import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.sti.cmart.entity.*;
import com.sti.cmart.model.dto.ServiceDTO;
import com.sti.cmart.model.dto.VehicleTypeDTO;
import com.sti.cmart.model.mapper.ServiceMapper;
import com.sti.cmart.other.request.DistanceRequest;
import com.sti.cmart.other.response.TripResponse;
import com.sti.cmart.repository.ServiceRepository;
import com.sti.cmart.service.GoogleMapService;
import com.sti.cmart.service.ProvinceService;
import com.sti.cmart.service.ServicesService;
import com.sti.cmart.service.VehicleTypeService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;
    private final GoogleMapService googleMapService;
    private final ProvinceService provinceService;
    private final VehicleTypeService vehicleTypeService;

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

    @Override
    public Object findByCityByAddress(DistanceRequest distanceRequest) throws IOException, InterruptedException, ApiException {
        String city = googleMapService.getCity(distanceRequest);
        Double price = 0.0;
        DistanceMatrix distanceMatrixElement = googleMapService.getDistance(distanceRequest);
//        Double price = service.get(0).getVehicleType().getRate() * googleMapService.getDistance(distanceRequest).rows[0].elements[0].distance.inMeters / 1000;
        String startLocation = distanceMatrixElement.originAddresses[0];
        String endLocation = distanceMatrixElement.destinationAddresses[0];
        List<Service> services = serviceRepository.findAllByRegionAppliesProvinceName(city);
        System.out.println(services);// tìm dịch vụ theo tên thành phố
        for (Service service : services) {
            double moneyDefault = 2 * 20000;
            double moneyPerKm = 10000 * ((double) (distanceMatrixElement.rows[0].elements[0].distance.inMeters / 1000) - 2);
            price = service.getVehicleType().getRate() * (moneyDefault + moneyPerKm);
        }
//        GeocodingResult[] endLocation = googleMapService.getAddress(new LatLng(distanceRequest.getFinishLat(), distanceRequest.getFinishLng()));
        return TripResponse.builder()
                .startLocation(startLocation)
                .endLocation(endLocation)
                .duration(googleMapService.getDistance(distanceRequest).rows[0].elements[0].duration.humanReadable)
                .distance(googleMapService.getDistance(distanceRequest).rows[0].elements[0].distance.humanReadable)
                .price(price)
                .build();
    }

}
