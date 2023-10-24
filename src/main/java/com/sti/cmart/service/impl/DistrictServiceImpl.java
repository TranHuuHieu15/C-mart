package com.sti.cmart.service.impl;

import com.sti.cmart.entity.District;
import com.sti.cmart.model.dto.DistrictDTO;
import com.sti.cmart.model.mapper.DistrictMapper;
import com.sti.cmart.repository.DistrictRepository;
import com.sti.cmart.service.DistrictService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;

    //findById
    @Override
    public DistrictDTO findById(Long id) {
        Optional<District> dto = districtRepository.findById(id);
        return dto.map(districtMapper::apply).orElse(null);
    }

    //findAll
    @Override
    public Page<DistrictDTO> findAll(SearchCriteria searchCriteria) {
        Page<District> list = districtRepository.findAll(Search.getPageable(searchCriteria));
        return list.map(districtMapper::apply);
    }

    //findByName
    @Override
    public DistrictDTO findByName(String name) {
        Optional<District> dto = districtRepository.findByName(name);
        return dto.map(districtMapper::apply).orElse(null);
    }

    //save
    @Override
    public DistrictDTO save(DistrictDTO districtDTO) {
        District district = districtMapper.applyToDistrictDTO(districtDTO);
        district = districtRepository.save(district);
        return districtMapper.apply(district);
    }

    //delete
    @Override
    public void delete(Long id) {
        districtRepository.deleteById(id);
    }
}
