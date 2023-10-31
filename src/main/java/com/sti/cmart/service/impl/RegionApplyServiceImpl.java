package com.sti.cmart.service.impl;

import com.sti.cmart.entity.RegionApply;
import com.sti.cmart.model.dto.RegionApplyDTO;
import com.sti.cmart.model.mapper.RegionApplyMapper;
import com.sti.cmart.repository.RegionApplyRepository;
import com.sti.cmart.service.RegionApplyService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegionApplyServiceImpl implements RegionApplyService {

    private final RegionApplyRepository regionApplyRepository;
    private final RegionApplyMapper regionApplyMapper;

    //findById
    @Override
    public RegionApplyDTO findById(Long id) {
        Optional<RegionApply> regionApply = regionApplyRepository.findById(id);
        return regionApply.map(regionApplyMapper::apply).orElse(null);
    }

    //findAll
    @Override
    public Page<RegionApplyDTO> findAll(SearchCriteria searchCriteria) {
        Page<RegionApply> list = regionApplyRepository.findAll(Search.getPageable(searchCriteria));
        return list.map(regionApplyMapper::apply);
    }

    //save
    @Override
    public RegionApplyDTO save(RegionApplyDTO regionApplyDTO) {
        RegionApply regionApply = regionApplyMapper.applyToRegionApply(regionApplyDTO);
        return regionApplyMapper.apply(regionApplyRepository.save(regionApply));
    }

    //detele
    @Override
    public void delete(Long id) {
        regionApplyRepository.deleteById(id);
    }

}
