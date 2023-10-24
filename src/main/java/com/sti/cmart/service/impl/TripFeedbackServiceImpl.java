package com.sti.cmart.service.impl;

import com.sti.cmart.entity.TripFeedback;
import com.sti.cmart.model.dto.TripFeedbackDTO;
import com.sti.cmart.model.mapper.TripFeedbackMapper;
import com.sti.cmart.repository.TripFeedbackRepository;
import com.sti.cmart.service.TripFeedbackService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TripFeedbackServiceImpl implements TripFeedbackService {

    private final TripFeedbackRepository tripFeedbackRepository;
    private final TripFeedbackMapper tripFeedbackMapper;

    //findById
    @Override
    public TripFeedbackDTO findById(Long id) {
        Optional<TripFeedback> tripFeedback = tripFeedbackRepository.findById(id);
        return tripFeedback.map(tripFeedbackMapper::apply).orElse(null);
    }

    //findAll
    @Override
    public Page<TripFeedbackDTO> findAll(SearchCriteria searchCriteria) {
        Page<TripFeedback> tripFeedbacks = tripFeedbackRepository.findAll(Search.getPageable(searchCriteria));
        return tripFeedbacks.map(tripFeedbackMapper::apply);
    }

    //save
    @Override
    public TripFeedbackDTO save(TripFeedbackDTO tripFeedbackDTO) {
        TripFeedback tripFeedback = tripFeedbackMapper.applyToTripFeedback(tripFeedbackDTO);
        return tripFeedbackMapper.apply(tripFeedbackRepository.save(tripFeedback));
    }

    //delete
    @Override
    public void delete(Long id) {
        tripFeedbackRepository.deleteById(id);
    }
}
