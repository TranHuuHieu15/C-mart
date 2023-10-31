package com.sti.cmart.service;

import com.sti.cmart.model.dto.RegionApplyDTO;
import com.sti.cmart.util.SearchCriteria;
import org.springframework.data.domain.Page;

public interface RegionApplyService {
    //findById
    RegionApplyDTO findById(Long id);

    //findAll
    Page<RegionApplyDTO> findAll(SearchCriteria searchCriteria);

    //save
    RegionApplyDTO save(RegionApplyDTO regionApplyDTO);

    //detele
    void delete(Long id);
}
