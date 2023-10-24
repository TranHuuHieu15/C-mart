package com.sti.cmart.service;

import com.sti.cmart.model.dto.EWalletDTO;
import com.sti.cmart.util.SearchCriteria;
import org.springframework.data.domain.Page;

public interface EWalletService {
    // find by id
    EWalletDTO findById(Long id);

    // find by bank
    Page<EWalletDTO> findAllByBank(String bank, SearchCriteria searchCriteria);

    //findAll
    Page<EWalletDTO> findAll(SearchCriteria searchCriteria);

    //findAllByStatus
    Page<EWalletDTO> findAllByStatus(Short status, SearchCriteria searchCriteria);

    //save
    EWalletDTO save(EWalletDTO eWalletDTO);

    //delete
    void delete(Long id);
}
