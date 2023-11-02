package com.sti.cmart.facade;

import com.sti.cmart.entity.Ward;
import com.sti.cmart.exception.common.InvalidParamException;
import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.exception.entity.EntityAlreadyExistException;
import com.sti.cmart.exception.entity.EntityNotFoundException;
import com.sti.cmart.model.dto.WardDTO;
import com.sti.cmart.service.WardService;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WardFacade {

    private final WardService wardService;

    //findById
    public WardDTO findById(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        WardDTO dto = wardService.findById(id);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //findByName
    public Page<WardDTO> findByName(String name, SearchCriteria searchCriteria) throws ArchitectureException {
        if (name == null)
            throw new InvalidParamException();
        Page<WardDTO> dto = wardService.findByName(name, searchCriteria);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //findAll
    public Page<WardDTO> findAll(SearchCriteria searchCriteria) throws ArchitectureException {
        Page<WardDTO> list = wardService.findAll(searchCriteria);
        if (list.isEmpty())
            throw new EntityNotFoundException();
        return list;
    }

    //save
    public WardDTO save(WardDTO wardDTO) throws ArchitectureException {
        if (wardDTO == null)
            throw new InvalidParamException();
        WardDTO dto = wardService.findById(wardDTO.getId());
        if (dto != null)
            throw new EntityAlreadyExistException();
        return wardService.save(wardDTO);
    }

    //update
    public WardDTO update(Long id,WardDTO wardDTO) throws ArchitectureException {
        if (wardDTO == null || id == null)
            throw new InvalidParamException();
        WardDTO ward = wardService.findById(id);
        return wardService.updateById(id, wardDTO);
    }

    //delete
    public void delete(Long id) throws ArchitectureException {
        findById(id);
        wardService.delete(id);
    }
}
