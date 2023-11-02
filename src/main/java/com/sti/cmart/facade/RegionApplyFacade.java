package com.sti.cmart.facade;

import com.sti.cmart.exception.common.InvalidParamException;
import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.exception.entity.EntityAlreadyExistException;
import com.sti.cmart.exception.entity.EntityNotFoundException;
import com.sti.cmart.model.dto.RegionApplyDTO;
import com.sti.cmart.service.RegionApplyService;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegionApplyFacade {

    private final RegionApplyService regionApplyService;

    //findById
    public RegionApplyDTO findById(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        RegionApplyDTO dto = regionApplyService.findById(id);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //findAll
    public Page<RegionApplyDTO> findAll(SearchCriteria searchCriteria) throws ArchitectureException {
        Page<RegionApplyDTO> list = regionApplyService.findAll(searchCriteria);
        if (list.isEmpty())
            throw new EntityNotFoundException();
        return list;
    }

    //save
    public RegionApplyDTO save(RegionApplyDTO dto) throws ArchitectureException {
        if (dto == null)
            throw new InvalidParamException();
        RegionApplyDTO regionApplyDTO = regionApplyService.findById(dto.getId());
        if (regionApplyDTO != null)
            throw new EntityAlreadyExistException();
        return regionApplyService.save(dto);
    }

    //update
    public RegionApplyDTO update(Long id,RegionApplyDTO dto) throws ArchitectureException {
        if (dto == null || id == null)
            throw new InvalidParamException();
        RegionApplyDTO regionApplyDTO = regionApplyService.findById(id);
        if (regionApplyDTO == null)
            throw new EntityNotFoundException();
        regionApplyDTO.setId(dto.getId());
        regionApplyDTO.setServiceId(dto.getServiceId());
        regionApplyDTO.setProvinceId(dto.getProvinceId());
        return regionApplyService.save(regionApplyDTO);
    }

    //detele
    public void delete(Long id) throws ArchitectureException {
        findById(id);
        regionApplyService.delete(id);
    }

}
