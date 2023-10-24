package com.sti.cmart.facade;

import com.sti.cmart.entity.Voucher;
import com.sti.cmart.exception.common.InvalidParamException;
import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.exception.entity.EntityAlreadyExistException;
import com.sti.cmart.exception.entity.EntityNotFoundException;
import com.sti.cmart.model.dto.VoucherDTO;
import com.sti.cmart.repository.VoucherRepository;
import com.sti.cmart.service.VoucherService;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoucherFacade {

    private final VoucherService voucherService;
    private final VoucherRepository voucherRepository;

    //findById
    public VoucherDTO findById(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        VoucherDTO dto = voucherService.findById(id);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //findAll
    public Page<VoucherDTO> findAll(SearchCriteria searchCriteria) throws ArchitectureException {
        Page<VoucherDTO> list = voucherService.findAll(searchCriteria);
        if (list.isEmpty())
            throw new EntityNotFoundException();
        return list;
    }

    //save
    public VoucherDTO save(VoucherDTO dto) throws ArchitectureException {
        if (dto == null)
            throw new InvalidParamException();
        VoucherDTO voucher = voucherService.findById(dto.getId());
        if (voucher != null)
            throw new EntityAlreadyExistException();
        return voucherService.save(dto);
    }

    //update
    public VoucherDTO update(VoucherDTO dto) throws ArchitectureException {
        if (dto == null)
            throw new InvalidParamException();
        findById(dto.getId());
        return voucherService.save(dto);
    }

    //delete
    public void delete(Long id) throws ArchitectureException {
        findById(id);
        voucherService.delete(id);
    }

}
