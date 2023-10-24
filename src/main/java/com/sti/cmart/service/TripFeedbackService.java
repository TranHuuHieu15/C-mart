package com.sti.cmart.service;

import com.sti.cmart.model.dto.TripFeedbackDTO;
import com.sti.cmart.util.SearchCriteria;
import org.springframework.data.domain.Page;

public interface TripFeedbackService {
    //findById
    TripFeedbackDTO findById(Long id);

    //findAll
    Page<TripFeedbackDTO> findAll(SearchCriteria searchCriteria);

    //save
    TripFeedbackDTO save(TripFeedbackDTO tripFeedbackDTO);

    //delete
    void delete(Long id);
}
