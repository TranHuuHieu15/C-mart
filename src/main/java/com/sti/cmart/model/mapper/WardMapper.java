package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.Address;
import com.sti.cmart.entity.District;
import com.sti.cmart.entity.Ward;
import com.sti.cmart.model.dto.WardDTO;
import com.sti.cmart.repository.AddressRepository;
import com.sti.cmart.repository.DistrictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class WardMapper implements Function<Ward, WardDTO> {

    private final DistrictRepository districtRepository;

    @Override
    public WardDTO apply(Ward ward) {
        return WardDTO.builder()
                .id(ward.getId())
                .name(ward.getName())
                .districtId(ward.getDistricts().getId())
                .build();
    }

    public Ward applyToWard(WardDTO dto) {
        District district = districtRepository.findById(dto.getDistrictId()).get();
        return Ward.builder()
                .id(dto.getId())
                .name(dto.getName())
                .districts(district)
                .build();
    }

}
