package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.District;
import com.sti.cmart.entity.Province;
import com.sti.cmart.model.dto.DistrictDTO;
import com.sti.cmart.repository.ProvinceRepository;
import com.sti.cmart.repository.WardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class DistrictMapper implements Function<District, DistrictDTO> {

    private final ProvinceRepository provinceRepository;

    @Override
    public DistrictDTO apply(District district) {
        return DistrictDTO.builder()
                .id(district.getId())
                .name(district.getName())
                .provinceId(district.getProvince().getId())
                .build();
    }

    public District applyToDistrictDTO(DistrictDTO districtDTO) {
        Province province = provinceRepository.findById(districtDTO.getProvinceId()).get();
        return District.builder()
                .id(districtDTO.getId())
                .name(districtDTO.getName())
                .province(province)
                .build();
    }
}
