package com.sti.cmart.service.impl;

import com.sti.cmart.entity.Ward;
import com.sti.cmart.model.dto.WardDTO;
import com.sti.cmart.model.mapper.WardMapper;
import com.sti.cmart.repository.WardRepository;
import com.sti.cmart.service.WardService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WardServiceImpl implements WardService {

    private final WardRepository wardRepository;
    private final WardMapper wardMapper;

    //findById
    @Override
    public WardDTO findById(Long id) {
        Optional<Ward> ward = wardRepository.findById(id);
        return ward.map(wardMapper::apply).orElse(null);
    }

    //findAll
    @Override
    public Page<WardDTO> findAll(SearchCriteria searchCriteria) {
        Page<Ward> list = wardRepository.findAll(Search.getPageable(searchCriteria));
        return list.map(wardMapper::apply);
    }

    //findByName
    @Override
    public WardDTO findByName(String name) {
        Optional<Ward> ward = wardRepository.findByName(name);
        return ward.map(wardMapper::apply).orElse(null);
    }

    //save
    @Override
    public WardDTO save(WardDTO wardDTO) {
        Ward ward = wardMapper.applyToWard(wardDTO);
        return wardMapper.apply(wardRepository.save(ward));
    }

    //delete
    @Override
    public void delete(Long id) {
        wardRepository.deleteById(id);
    }

}
