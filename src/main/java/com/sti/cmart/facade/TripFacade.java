package com.sti.cmart.facade;

import com.sti.cmart.exception.common.InvalidParamException;
import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.exception.entity.EntityAlreadyExistException;
import com.sti.cmart.exception.entity.EntityNotFoundException;
import com.sti.cmart.model.dto.TripDTO;
import com.sti.cmart.other.request.DistanceRequest;
import com.sti.cmart.repository.RoleRepository;
import com.sti.cmart.service.GoogleMapService;
import com.sti.cmart.service.ServicesService;
import com.sti.cmart.service.TripService;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TripFacade {

    private final TripService tripService;
    private final GoogleMapService googleMapService;
    private final ServicesService servicesService;
    private final RoleRepository roleRepository;

    //findByid
    public Object findById(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        TripDTO dto = tripService.findById(id);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //findAll
    public Object findAll(SearchCriteria searchCriteria) throws ArchitectureException {
        Page<TripDTO> list = tripService.findAll(searchCriteria);
        if (list.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return list;
    }

    //save
    public Object save(TripDTO tripDTO) throws ArchitectureException {
        if (tripDTO == null)
            throw new InvalidParamException();
        TripDTO dto = tripService.findById(tripDTO.getId());
        if (dto != null)
            throw new EntityAlreadyExistException();
//        tripDTO.setDriver();
//        tripDTO.setDate(new Date());
//        tripDTO.setDistance(googleMapService.getDistance(new DistanceRequest()).rows[0].elements[0].distance.humanReadable);
        return tripService.save(tripDTO);
    }

    //update
    public Object update(Long id, TripDTO tripDTO) throws ArchitectureException {
        if (tripDTO == null || id == null)
            throw new InvalidParamException();
        TripDTO dto = tripService.findById(id);
        dto.setDriver(tripDTO.getDriver());
        dto.setAddressId(tripDTO.getAddressId());
        dto.setServiceId(tripDTO.getServiceId());
        dto.setPaymentId(tripDTO.getPaymentId());
        dto.setCustomer(tripDTO.getCustomer());
        dto.setPrice(tripDTO.getPrice());
        dto.setPickUpTime(tripDTO.getPickUpTime());
        dto.setStatus(tripDTO.getStatus());
        dto.setStartLocation(tripDTO.getStartLocation());
        dto.setEndLocation(tripDTO.getEndLocation());
        dto.setDistance(tripDTO.getDistance());
        dto.setDate(tripDTO.getDate());
        dto.setDropTime(tripDTO.getDropTime());
        if (dto == null)
            throw new EntityNotFoundException();
        return tripService.save(dto);
    }

    //delete
    public void delete(Long id) throws ArchitectureException {
        findById(id);
        tripService.delete(id);
    }
}
