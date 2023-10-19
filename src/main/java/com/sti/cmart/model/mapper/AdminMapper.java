package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.Admin;
import com.sti.cmart.model.dto.AdminDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AdminMapper implements Function<Admin, AdminDTO> {

    @Override
    public AdminDTO apply(Admin admin) {
        return AdminDTO
                .builder()
                .id(admin.getId())
                .username(admin.getUsername())
                .password(admin.getPassword())
                .fullname(admin.getFullname())
                .phone(admin.getPhone())
                .email(admin.getEmail())
                .isActive(admin.getIsActive())
                .image(admin.getImage())
                .address(admin.getAddress())
                .status(admin.getStatus())
                .build();
    }

    public Admin applyToEntity(AdminDTO dto) {
        return Admin.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .fullname(dto.getFullname())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .isActive(dto.getIsActive())
                .image(dto.getImage())
                .address(dto.getAddress())
                .status(dto.getStatus())
                .build();
    }
}
