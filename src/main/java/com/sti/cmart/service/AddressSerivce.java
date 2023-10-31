package com.sti.cmart.service;

import com.sti.cmart.model.dto.AddressDTO;
import com.sti.cmart.util.SearchCriteria;
import org.springframework.data.domain.Page;

public interface AddressSerivce {
    //findById
    AddressDTO findById(Long id);

    //findAll
    Page<AddressDTO> findAll(SearchCriteria searchCriteria);

    //save
    AddressDTO save(AddressDTO addressDTO);

    //detele
    void delete(Long id);
}
