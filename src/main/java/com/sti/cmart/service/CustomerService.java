package com.sti.cmart.service;

import com.sti.cmart.model.dto.CustomerDTO;
import com.sti.cmart.util.SearchCriteria;
import org.springframework.data.domain.Page;

public interface CustomerService {
    //find by id
    CustomerDTO findById(Long id);

    //find by username
    CustomerDTO findByUsername(String username);

    //find by email
    CustomerDTO findByEmail(String email);

    //find all by fullname
    Page<CustomerDTO> findAllByFullname(String fullname, SearchCriteria searchCriteria);

    //find all customer
    Page<CustomerDTO> findAllCustomer(SearchCriteria searchCriteria);

    //save
    CustomerDTO save(CustomerDTO customerDTO);

    //delete
    void delete(Long id);

    //find by phone
    CustomerDTO findByPhone(String phone);
}
