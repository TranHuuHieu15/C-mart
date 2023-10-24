package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.Province;
import com.sti.cmart.model.dto.ProvinceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ProvinceMapper implements Function<Province, ProvinceDTO> {
    @Override
    public ProvinceDTO apply(Province province) {
        return ProvinceDTO.builder()
                .id(province.getId())
                .name(province.getName())
                .description(province.getDescription())
                .build();
    }

    public Province applyToProvince(ProvinceDTO provinceDTO) {
        return Province.builder()
                .id(provinceDTO.getId())
                .name(provinceDTO.getName())
                .description(provinceDTO.getDescription())
                .build();
    }

}
