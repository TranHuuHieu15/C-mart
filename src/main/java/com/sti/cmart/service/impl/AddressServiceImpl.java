package com.sti.cmart.service.impl;

import com.sti.cmart.entity.Address;
import com.sti.cmart.model.dto.AddressDTO;
import com.sti.cmart.model.mapper.AddressMapper;
import com.sti.cmart.repository.AddressRepository;
import com.sti.cmart.service.AddressSerivce;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressSerivce {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    //findById
    @Override
    public AddressDTO findById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        return address.map(addressMapper::apply).orElse(null);
    }

    //findAll
    @Override
    public Page<AddressDTO> findAll(SearchCriteria searchCriteria) {
        Page<Address> list = addressRepository.findAll(Search.getPageable(searchCriteria));
        return list.map(addressMapper::apply);
    }

    //save
    @Override
    public AddressDTO save(AddressDTO addressDTO) {
        Address address = addressMapper.applyToAddress(addressDTO);
        return addressMapper.apply(addressRepository.save(address));
    }

    //detele
    @Override
    public void delete(Long id) {
        addressRepository.deleteById(id);
    }

}