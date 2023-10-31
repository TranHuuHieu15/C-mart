package com.sti.cmart.service;

import com.sti.cmart.model.dto.FeedBackSysDTO;
import com.sti.cmart.util.SearchCriteria;
import org.springframework.data.domain.Page;

public interface FeedbackSysService {
    //findById
    FeedBackSysDTO findById(Long id);

    //findAll
    Page<FeedBackSysDTO> findAll(SearchCriteria searchCriteria);

    //save
    FeedBackSysDTO save(FeedBackSysDTO dto);

    //delete
    void delete(Long id);
}
