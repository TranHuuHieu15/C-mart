package com.sti.cmart.service.impl;

import com.sti.cmart.entity.Province;
import com.sti.cmart.model.dto.ProvinceDTO;
import com.sti.cmart.model.mapper.ProvinceMapper;
import com.sti.cmart.repository.ProvinceRepository;
import com.sti.cmart.service.ProvinceService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {

    private final ProvinceRepository provinceRepository;
    private final ProvinceMapper provinceMapper;

    //findById
    @Override
    public ProvinceDTO findById(Long id) {
        Optional<Province> province = provinceRepository.findById(id);
        return province.map(provinceMapper::apply).orElse(null);
    }

    //findAll
    @Override
    public Page<ProvinceDTO> findAll(SearchCriteria searchCriteria) {
        Page<Province> list = provinceRepository.findAll(Search.getPageable(searchCriteria));
        return list.map(provinceMapper::apply);
    }

    //findByName
    @Override
    public Page<ProvinceDTO> findByName(String name, SearchCriteria searchCriteria) {
        Page<Province> list = provinceRepository.findByName(name, Search.getPageable(searchCriteria));
        return list.map(provinceMapper::apply);
    }

    @Override
    public ProvinceDTO findByName(String name) {
        Optional<Province> province = Optional.ofNullable(provinceRepository.findByName(name));
        return province.map(provinceMapper::apply).orElse(null);
    }

    //save
    @Override
    public ProvinceDTO save(ProvinceDTO provinceDTO) {
        Province province = provinceMapper.applyToProvince(provinceDTO);
        return provinceMapper.apply(provinceRepository.save(province));
    }

    //delete
    @Override
    public void delete(Long id) {
        provinceRepository.deleteById(id);
    }
}
