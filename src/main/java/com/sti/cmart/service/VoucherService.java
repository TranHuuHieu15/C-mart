package com.sti.cmart.service;

import com.sti.cmart.model.dto.VoucherDTO;
import com.sti.cmart.util.SearchCriteria;
import org.springframework.data.domain.Page;

public interface VoucherService {
    //findById
    VoucherDTO findById(Long id);

    //findAll
    Page<VoucherDTO> findAll(SearchCriteria searchCriteria);

    //findByName
    VoucherDTO findByName(String name);

    //save
    VoucherDTO save(VoucherDTO voucherDTO);

    //delete
    void delete(Long id);
}
