package com.sti.cmart.facade;

import com.sti.cmart.exception.common.InvalidParamException;
import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.exception.entity.EntityAlreadyExistException;
import com.sti.cmart.exception.entity.EntityNotFoundException;
import com.sti.cmart.model.dto.EWalletDTO;
import com.sti.cmart.service.EWalletService;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EWalletFacade {

    private final EWalletService eWalletService;

    //find by id
    public EWalletDTO findById(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        EWalletDTO eWalletDTO = eWalletService.findById(id);
        if (eWalletDTO == null)
            throw new EntityNotFoundException();
        return eWalletDTO;
    }

    //find by bank
    public Page<EWalletDTO> findAllByBank(String bank, SearchCriteria searchCriteria) throws ArchitectureException {
        if (bank == null)
            throw new InvalidParamException();
        Page<EWalletDTO> list = eWalletService.findAllByBank(bank, searchCriteria);
        if (list.isEmpty())
            throw new EntityNotFoundException();
        return list;
    }

    //findByStatus
    public Page<EWalletDTO> findAllByStatus(Short status, SearchCriteria searchCriteria) throws ArchitectureException {
        if (status == null)
            throw new InvalidParamException();
        Page<EWalletDTO> list = eWalletService.findAllByStatus(status, searchCriteria);
        if (list.isEmpty())
            throw new EntityNotFoundException();
        return list;
    }

    //findAll
    public Page<EWalletDTO> findAll(SearchCriteria searchCriteria) throws ArchitectureException {
        Page<EWalletDTO> list = eWalletService.findAll(searchCriteria);
        if (list.isEmpty())
            throw new EntityNotFoundException();
        return list;
    }
    //save
    public EWalletDTO save(EWalletDTO eWalletDTO) throws ArchitectureException {
        if (eWalletDTO == null)
            throw new InvalidParamException();
        EWalletDTO dto = eWalletService.findById(eWalletDTO.getId());
        if (dto != null)
            throw new EntityAlreadyExistException();
        return eWalletService.save(eWalletDTO);
    }
    //update
    public EWalletDTO update(EWalletDTO eWalletDTO) throws ArchitectureException {
        if (eWalletDTO == null)
            throw new InvalidParamException();
        EWalletDTO dto = eWalletService.findById(eWalletDTO.getId());
        if (dto == null)
            throw new EntityNotFoundException();
        return eWalletService.save(eWalletDTO);
    }

    //delete
    public void delete(Long id) throws ArchitectureException {
        findById(id);
        eWalletService.delete(id);
    }
}
