package com.sti.cmart.service.impl;

import com.sti.cmart.entity.District;
import com.sti.cmart.entity.Province;
import com.sti.cmart.model.dto.DistrictDTO;
import com.sti.cmart.model.mapper.DistrictMapper;
import com.sti.cmart.repository.DistrictRepository;
import com.sti.cmart.repository.ProvinceRepository;
import com.sti.cmart.service.DistrictService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;
    private final ProvinceRepository provinceRepository;

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
    public Page<DistrictDTO> findByName(String name, SearchCriteria searchCriteria) {
        Page<District> dto = districtRepository.findByName(name, Search.getPageable(searchCriteria));
        return dto.map(districtMapper::apply);
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

    @Override
    public DistrictDTO update(Long id, DistrictDTO dto) {
        District existingDistrict = districtRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("District not found with id: " + id));

        // Update the existing District entity with the new values from DTO
        existingDistrict.setName(dto.getName());

        // Get the Province from the repository based on the provided provinceId in DTO
        Province province = provinceRepository.findById(dto.getProvinceId())
                .orElseThrow(() -> new EntityNotFoundException("Province not found with id: " + dto.getProvinceId()));
        existingDistrict.setProvince(province);
        // Save the updated District entity
        District updatedDistrict = districtRepository.save(existingDistrict);
        // Map and return the updated DistrictDTO
        return districtMapper.apply(updatedDistrict);
    }
}
