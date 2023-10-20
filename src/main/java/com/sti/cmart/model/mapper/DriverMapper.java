package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.*;
import com.sti.cmart.model.dto.DriverDTO;
import com.sti.cmart.repository.ChatRepository;
import com.sti.cmart.repository.StaffRepository;
import com.sti.cmart.repository.TripRepository;
import com.sti.cmart.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class DriverMapper implements Function<Driver, DriverDTO> {
    private final StaffRepository staffRepository;
    private final TripRepository tripRepository;
    private final VehicleRepository vehicleRepository;
    private final ChatRepository chatRepository;

    @Override
    public DriverDTO apply(Driver driver) {
        return DriverDTO.builder()
                .id(driver.getId())
                .username(driver.getUsername())
                .password(driver.getPassword())
                .fullname(driver.getFullname())
                .phone(driver.getPhone())
                .email(driver.getEmail())
                .isActive(driver.getIsActive())
                .address(driver.getAddress())
                .image(driver.getImage())
                .staffId(driver.getStaff().getId())
                .status(driver.getStatus())
                .build();
    }

    public Driver applyToDriver(DriverDTO driverDTO) {
        Staff staff = staffRepository.findById(driverDTO.getStaffId()).get();
//        Trip trip = tripRepository.find(driverDTO.getId()).get();
        Vehicle vehicle = vehicleRepository.findById(driverDTO.getId()).get();
        Chat chat = chatRepository.findById(driverDTO.getId()).get();
        return Driver.builder()
                .id(driverDTO.getId())
                .username(driverDTO.getUsername())
                .password(driverDTO.getPassword())
                .fullname(driverDTO.getFullname())
                .phone(driverDTO.getPhone())
                .email(driverDTO.getEmail())
                .isActive(driverDTO.getIsActive())
                .address(driverDTO.getAddress())
                .image(driverDTO.getImage())
                .staff(staff)
                .status(driverDTO.getStatus())
// bổ sung thêm nì

                .build();
    }
}
