package com.sti.cmart.service.impl;

import com.sti.cmart.entity.Support;
import com.sti.cmart.model.dto.SupportDTO;
import com.sti.cmart.model.mapper.SupportMapper;
import com.sti.cmart.repository.SupportRepository;
import com.sti.cmart.service.SupportService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupportServiceImpl implements SupportService {
    private final SupportRepository supportRepository;
    private final SupportMapper supportMapper;

    //findById
    public SupportDTO findById(Long id) {
        Optional<Support> support = supportRepository.findById(id);
        return support.map(supportMapper::apply).orElse(null);
    }

    //findAll
    public Page<SupportDTO> findAll(SearchCriteria searchCriteria) {
        Page<Support> list = supportRepository.findAll(Search.getPageable(searchCriteria));
        return list.map(supportMapper::apply);
    }

    //save
    public SupportDTO save(SupportDTO supportDTO) {
        Support support = supportMapper.applyToSupport(supportDTO);
        return supportMapper.apply(supportRepository.save(support));
    }

    //delete
    public void delete(Long id) {
        supportRepository.deleteById(id);
    }

    //findByName
    public SupportDTO findByName(String name) {
        Optional<Support> support = supportRepository.findByName(name);
        return support.map(supportMapper::apply).orElse(null);
    }

    public SupportDTO findByPhone(String phone) {
        Optional<Support> support = supportRepository.findByPhone(phone);
        return support.map(supportMapper::apply).orElse(null);
    }

}
