package com.sti.cmart.facade;

import com.sti.cmart.exception.common.InvalidParamException;
import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.exception.entity.EntityAlreadyExistException;
import com.sti.cmart.exception.entity.EntityNotFoundException;
import com.sti.cmart.model.dto.FeedBackSysDTO;
import com.sti.cmart.service.FeedbackSysService;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackSysFacade {

    private final FeedbackSysService feedbackSysService;

    //findById
    public FeedBackSysDTO findById(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        FeedBackSysDTO dto = feedbackSysService.findById(id);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //findAll
    public Page<FeedBackSysDTO> findAll(SearchCriteria searchCriteria) throws ArchitectureException {
       Page<FeedBackSysDTO> list = feedbackSysService.findAll(searchCriteria);
        if (list.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return list;
    }

    //save
    public FeedBackSysDTO save(FeedBackSysDTO dto) throws ArchitectureException {
        if (dto == null)
            throw new InvalidParamException();
        FeedBackSysDTO feedBackSysDTO = feedbackSysService.findById(dto.getId());
        if (feedBackSysDTO != null)
            throw new EntityAlreadyExistException();
        return feedbackSysService.save(dto);
    }

    //update
    public FeedBackSysDTO update(FeedBackSysDTO dto) throws ArchitectureException {
        if (dto == null)
            throw new InvalidParamException();
        findById(dto.getId());
        return feedbackSysService.save(dto);
    }

    //delete
    public void delete(Long id) throws ArchitectureException {
        findById(id);
        feedbackSysService.delete(id);
    }

}
