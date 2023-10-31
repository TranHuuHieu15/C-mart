package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.RegionApply;
import com.sti.cmart.model.dto.RegionApplyDTO;
import com.sti.cmart.repository.ProvinceRepository;
import com.sti.cmart.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class RegionApplyMapper implements Function<RegionApply, RegionApplyDTO> {

    private final ProvinceRepository provinceRepository;
    private final ServiceRepository serviceRepository;

    @Override
    public RegionApplyDTO apply(RegionApply regionApply) {
        return RegionApplyDTO.builder()
                .id(regionApply.getId())
                .provinceId(regionApply.getProvince().getId())
                .serviceId(regionApply.getService().getId())
                .build();
    }

    public RegionApply applyToRegionApply(RegionApplyDTO dto) {
        return RegionApply.builder()
                .id(dto.getId())
                .province(provinceRepository.findById(dto.getProvinceId()).get())
                .service(serviceRepository.findById(dto.getServiceId()).get())
                .build();
    }

}
