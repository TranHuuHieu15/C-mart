package com.sti.cmart.facade;

import com.sti.cmart.exception.common.InvalidParamException;
import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.exception.entity.EntityAlreadyExistException;
import com.sti.cmart.exception.entity.EntityNotFoundException;
import com.sti.cmart.model.dto.SupportDTO;
import com.sti.cmart.service.SupportService;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupportFacade {

    private final SupportService supportService;

    //findById
    public SupportDTO findById(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        SupportDTO dto = supportService.findById(id);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //findAll
    public Page<SupportDTO> findAll(SearchCriteria searchCriteria) throws ArchitectureException {
        Page<SupportDTO> list = supportService.findAll(searchCriteria);
        if (list.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return list;
    }

    //findByName
    public SupportDTO findByName(String name) throws ArchitectureException {
        if (name == null)
            throw new InvalidParamException();
        SupportDTO dto = supportService.findByName(name);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //save
    public SupportDTO save(SupportDTO dto) throws ArchitectureException {
        if (dto == null)
            throw new InvalidParamException();
        SupportDTO supportDTO = supportService.findById(dto.getId());
        if (supportDTO != null)
            throw new EntityAlreadyExistException();
        return supportService.save(dto);
    }

    //update
    public SupportDTO update(SupportDTO dto) throws ArchitectureException {
        if (dto == null)
            throw new InvalidParamException();
        findById(dto.getId());
        return supportService.save(dto);
    }

    //delete
    public void delete(Long id) throws ArchitectureException {
        findById(id);
        supportService.delete(id);
    }
}
