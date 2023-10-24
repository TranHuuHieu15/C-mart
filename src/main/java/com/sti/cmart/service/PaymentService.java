package com.sti.cmart.service;

import com.sti.cmart.model.dto.PaymentDTO;
import com.sti.cmart.util.SearchCriteria;
import org.springframework.data.domain.Page;

public interface PaymentService {
    //findById
    PaymentDTO findById(Long id);

    //findAll
    Page<PaymentDTO> findAll(SearchCriteria searchCriteria);

    //save
    PaymentDTO save(PaymentDTO paymentDTO);

    //delete
    void delete(Long id);

    //findByName
    PaymentDTO findByName(String name);
}
