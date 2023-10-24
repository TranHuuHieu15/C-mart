package com.sti.cmart.facade;

import com.sti.cmart.exception.common.InvalidParamException;
import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.exception.entity.EntityAlreadyExistException;
import com.sti.cmart.exception.entity.EntityNotFoundException;
import com.sti.cmart.model.dto.ProvinceDTO;
import com.sti.cmart.service.ProvinceService;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProvinceFacade {

    private final ProvinceService provinceService;

    //findById
    public ProvinceDTO findById(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        ProvinceDTO dto = provinceService.findById(id);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //findAll
    public Page<ProvinceDTO> findAll(SearchCriteria searchCriteria) throws ArchitectureException {
        Page<ProvinceDTO> list = provinceService.findAll(searchCriteria);
        if (list.isEmpty())
            throw new EntityNotFoundException();
        return list;
    }

    //findByName
    public Page<ProvinceDTO> findByName(String name, SearchCriteria searchCriteria) throws ArchitectureException {
        Page<ProvinceDTO> list = provinceService.findByName(name, searchCriteria);
        if (list.isEmpty())
            throw new EntityNotFoundException();
        return list;
    }

    //save
    public ProvinceDTO save(ProvinceDTO provinceDTO) throws ArchitectureException {
        if (provinceDTO == null)
            throw new InvalidParamException();
        ProvinceDTO dto = provinceService.findById(provinceDTO.getId());
        if (dto != null)
            throw new EntityAlreadyExistException();
        return provinceService.save(provinceDTO);
    }

    //update
    public ProvinceDTO update(ProvinceDTO provinceDTO) throws ArchitectureException {
        if (provinceDTO == null)
            throw new InvalidParamException();
        findById(provinceDTO.getId());
        return provinceService.save(provinceDTO);
    }

    //delete
    public void delete(Long id) throws ArchitectureException {
        findById(id);
        provinceService.delete(id);
    }
}
