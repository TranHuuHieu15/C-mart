package com.sti.cmart.service;

import com.sti.cmart.model.dto.TransactionDTO;
import com.sti.cmart.util.SearchCriteria;
import org.springframework.data.domain.Page;

public interface TransactionService {
    //findById
    TransactionDTO findById(Long id);

    //findAll
    Page<TransactionDTO> findAll(SearchCriteria searchCriteria);

    //save
    TransactionDTO save(TransactionDTO transactionDTO);

    //delete
    void delete(Long id);
}
