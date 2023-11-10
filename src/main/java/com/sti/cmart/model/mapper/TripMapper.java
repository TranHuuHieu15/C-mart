package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.Role;
import com.sti.cmart.entity.Trip;
import com.sti.cmart.model.dto.TripDTO;
import com.sti.cmart.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TripMapper implements Function<Trip, TripDTO> {

    private final AddressRepository addressRepository;
    private final ServiceRepository serviceRepository;
    private final PaymentRepository paymentRepository;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final TripRepository tripRepository;


    @Override
    public TripDTO apply(Trip trip) {
        return TripDTO.builder()
                .id(trip.getId())
                .serviceId(trip.getService().getId())
                .addressId(trip.getAddress().getId())
                .date(trip.getDate())
                .driver(trip.getDriver().getId())
                .price(trip.getPrice())
                .paymentId(trip.getPayment().getId())
                .pickUpTime(trip.getPickUpTime())
                .status(trip.getStatus())
                .customer(trip.getUser().getId())
                .dropTime(trip.getDropTime())
                .endLocation(trip.getEndLocation())
                .startLocation(trip.getStartLocation())
                .distance(trip.getDistance())
                .build();
    }

    public Trip applyToTrip(TripDTO tripDTO) {
        Trip trip = Trip.builder()
                .date(tripDTO.getDate())
                .distance(tripDTO.getDistance())
                .dropTime(tripDTO.getDropTime())
                .endLocation(tripDTO.getEndLocation())
                .startLocation(tripDTO.getStartLocation())
                .price(tripDTO.getPrice())
                .pickUpTime(tripDTO.getPickUpTime())
                .status(tripDTO.getStatus())
                .build();

        if (tripDTO.getServiceId() != null) {
            trip.setService(serviceRepository.findById(tripDTO.getServiceId())
                    .orElseThrow(() -> new EntityNotFoundException("Service not found")));
        }

        if (tripDTO.getAddressId() != null) {
            trip.setAddress(addressRepository.findById(tripDTO.getAddressId())
                    .orElseThrow(() -> new EntityNotFoundException("Address not found")));
        }

        if (tripDTO.getPaymentId() != null) {
            trip.setPayment(paymentRepository.findById(tripDTO.getPaymentId())
                    .orElseThrow(() -> new EntityNotFoundException("Payment not found")));
        }


        if (tripDTO.getCustomer() != null) {
            trip.setUser(roleRepository.findByAccount_Id(tripDTO.getCustomer()));
//                    .orElseThrow(() -> new EntityNotFoundException("Customer not found")));
        }

        if (tripDTO.getDriver() != null) {
            trip.setDriver(roleRepository.findByAccount_Id(tripDTO.getDriver()));
//                    .orElseThrow(() -> new EntityNotFoundException("Driver not found")));
        }

        return trip;
    }
}
