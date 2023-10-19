package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.Admin;
import com.sti.cmart.entity.Staff;
import com.sti.cmart.model.dto.StaffDTO;
import com.sti.cmart.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class StaffMapper implements Function<Staff, StaffDTO> {

    private final AdminRepository adminRepository;

    @Override
    public StaffDTO apply(Staff staff) {
        return StaffDTO.builder()
                .id(staff.getId())
                .address(staff.getAddress())
                .email(staff.getEmail())
                .phone(staff.getPhone())
                .fullname(staff.getFullname())
                .image(staff.getImage())
                .password(staff.getPassword())
                .username(staff.getUsername())
                .status(staff.getStatus())
                .isActive(staff.getIsActive())
                .adminId(staff.getAdmin().getId())
                .build();
    }

    public Staff applyToStaff(StaffDTO staffDTO) {
        Admin admin = adminRepository.findById(staffDTO.getAdminId()).get();
        return Staff.builder()
                .id(staffDTO.getId())
                .username(staffDTO.getUsername())
                .password(staffDTO.getPassword())
                .fullname(staffDTO.getFullname())
                .phone(staffDTO.getPhone())
                .email(staffDTO.getEmail())
                .isActive(staffDTO.getIsActive())
                .address(staffDTO.getAddress())
                .image(staffDTO.getImage())
                .status(staffDTO.getStatus())
                .admin(admin)
                .build();
    }
}
