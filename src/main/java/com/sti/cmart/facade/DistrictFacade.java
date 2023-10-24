package com.sti.cmart.facade;

import com.sti.cmart.exception.common.InvalidParamException;
import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.exception.entity.EntityAlreadyExistException;
import com.sti.cmart.exception.entity.EntityNotFoundException;
import com.sti.cmart.model.dto.DistrictDTO;
import com.sti.cmart.service.DistrictService;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistrictFacade {

    private final DistrictService districtService;

    //findById
    public DistrictDTO findById(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        DistrictDTO dto = districtService.findById(id);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //findByName
    public DistrictDTO findByName(String name) throws ArchitectureException {
        if (name == null)
            throw new InvalidParamException();
        DistrictDTO dto = districtService.findByName(name);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //findAll
    public Page<DistrictDTO> findAll(SearchCriteria searchCriteria) throws ArchitectureException {
        Page<DistrictDTO> list = districtService.findAll(searchCriteria);
        if (list.isEmpty())
            throw new EntityNotFoundException();
        return list;
    }

    //save
    public DistrictDTO save(DistrictDTO districtDTO) throws ArchitectureException {
        if (districtDTO == null)
            throw new InvalidParamException();
        DistrictDTO dto = districtService.findById(districtDTO.getId());
        if (dto != null)
            throw new EntityAlreadyExistException();
        return districtService.save(districtDTO);
    }

    //update
    public DistrictDTO update(DistrictDTO districtDTO) throws ArchitectureException {
        if (districtDTO == null)
            throw new InvalidParamException();
        findById(districtDTO.getId());
        return districtService.save(districtDTO);
    }

    //delete
    public void delete(Long id) throws ArchitectureException {
        findById(id);
        districtService.delete(id);
    }
}
