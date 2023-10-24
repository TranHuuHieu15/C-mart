package com.sti.cmart.facade;

import com.sti.cmart.entity.TripFeedback;
import com.sti.cmart.exception.common.InvalidParamException;
import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.exception.entity.EntityAlreadyExistException;
import com.sti.cmart.exception.entity.EntityNotFoundException;
import com.sti.cmart.model.dto.TripFeedbackDTO;
import com.sti.cmart.service.TripFeedbackService;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripFeedbackFacade {

    private final TripFeedbackService tripFeedbackService;

    //findById
    public TripFeedbackDTO findById(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        TripFeedbackDTO dto = tripFeedbackService.findById(id);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //findAll
    public Page<TripFeedbackDTO> findAll(SearchCriteria searchCriteria) throws ArchitectureException {
        Page<TripFeedbackDTO> list = tripFeedbackService.findAll(searchCriteria);
        if (list.isEmpty())
            throw new EntityNotFoundException();
        return list;
    }

    //save
    public TripFeedbackDTO save(TripFeedbackDTO dto) throws ArchitectureException {
        if (dto == null)
            throw new InvalidParamException();
        TripFeedbackDTO tripFeedbackDTO = tripFeedbackService.findById(dto.getId());
        if (tripFeedbackDTO != null)
            throw new EntityAlreadyExistException();
        return tripFeedbackService.save(dto);
    }

    //update
    public TripFeedbackDTO update(TripFeedbackDTO dto) throws ArchitectureException {
        if (dto == null)
            throw new InvalidParamException();
        findById(dto.getId());
        return tripFeedbackService.save(dto);
    }

    //delete
    public void delete(Long id) throws ArchitectureException {
        findById(id);
        tripFeedbackService.delete(id);
    }

}
