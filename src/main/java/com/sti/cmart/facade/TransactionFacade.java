package com.sti.cmart.facade;

import com.sti.cmart.exception.common.InvalidParamException;
import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.exception.entity.EntityAlreadyExistException;
import com.sti.cmart.exception.entity.EntityNotFoundException;
import com.sti.cmart.model.dto.TransactionDTO;
import com.sti.cmart.service.TransactionService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionFacade {

    private final TransactionService transactionService;

    //findById
    public TransactionDTO findById(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        TransactionDTO dto = transactionService.findById(id);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //findAll
    public Page<TransactionDTO> findAll(SearchCriteria searchCriteria) throws ArchitectureException {
        Page<TransactionDTO> list = transactionService.findAll(searchCriteria);
        if (list.isEmpty())
            throw new EntityNotFoundException();
        return list;
    }

    //save
    public TransactionDTO save(TransactionDTO transactionDTO) throws ArchitectureException {
        if (transactionDTO == null)
            throw new InvalidParamException();
        TransactionDTO dto = transactionService.findById(transactionDTO.getId());
        if (dto != null)
            throw new EntityAlreadyExistException();
        return transactionService.save(transactionDTO);
    }

    //update
    public TransactionDTO update(TransactionDTO transactionDTO) throws ArchitectureException {
        if (transactionDTO == null)
            throw new InvalidParamException();
        findById(transactionDTO.getId());
        return transactionService.save(transactionDTO);
    }

    //Delete
    public void delete(Long id) throws ArchitectureException {
        findById(id);
        transactionService.delete(id);
    }
}
