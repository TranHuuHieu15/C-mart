package com.sti.cmart.service.impl;

import com.sti.cmart.entity.FeedbackSy;
import com.sti.cmart.model.dto.FeedBackSysDTO;
import com.sti.cmart.model.mapper.FeedBackSysMapper;
import com.sti.cmart.repository.FeedbackSysRepository;
import com.sti.cmart.service.FeedbackSysService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackSysServiceImpl implements FeedbackSysService {

    private final FeedbackSysRepository feedbackSysRepository;
    private final FeedBackSysMapper feedbackSysMapper;

    //findById
    @Override
    public FeedBackSysDTO findById(Long id) {
        Optional<FeedbackSy> dto = feedbackSysRepository.findById(id);
        return dto.map(feedbackSysMapper::apply).orElse(null);
    }

    //findAll
    @Override
    public Page<FeedBackSysDTO> findAll(SearchCriteria searchCriteria) {
        Page<FeedbackSy> dto = feedbackSysRepository.findAll(Search.getPageable(searchCriteria));
        return dto.map(feedbackSysMapper::apply);
    }

    //save
    @Override
    public FeedBackSysDTO save(FeedBackSysDTO dto) {
        FeedbackSy feedbackSys = feedbackSysMapper.applyToFeebackSys(dto);
        return feedbackSysMapper.apply(feedbackSysRepository.save(feedbackSys));
    }

    //delete
    @Override
    public void delete(Long id) {
        feedbackSysRepository.deleteById(id);
    }
}
