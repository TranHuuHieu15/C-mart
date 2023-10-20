package com.sti.cmart.service.impl;

import com.sti.cmart.entity.Customer;
import com.sti.cmart.model.dto.CustomerDTO;
import com.sti.cmart.model.mapper.CustomerMapper;
import com.sti.cmart.repository.CustomerRepository;
import com.sti.cmart.service.CustomerService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    //find by id
    @Override
    public CustomerDTO findById(Long id) {
        Optional<Customer> dto = customerRepository.findById(id);
        return dto.map(customerMapper::apply).orElse(null);
    }

    //find by username
    @Override
    public CustomerDTO findByUsername(String username) {
        Optional<Customer> dto = customerRepository.findByUsername(username);
        return dto.map(customerMapper::apply).orElse(null);
    }

    //find by email
    @Override
    public CustomerDTO findByEmail(String email) {
        Optional<Customer> dto = customerRepository.findByEmail(email);
        return dto.map(customerMapper::apply).orElse(null);
    }

    //find all by fullname
    @Override
    public Page<CustomerDTO> findAllByFullname(String fullname, SearchCriteria searchCriteria) {
        Page<Customer> customers = customerRepository.findAllByFullname(fullname, Search.getPageable(searchCriteria));
        return customers.map(customerMapper::apply);
    }

    //find all customer
    @Override
    public Page<CustomerDTO> findAllCustomer(SearchCriteria searchCriteria) {
        Page<Customer> customers = customerRepository.findAll(Search.getPageable(searchCriteria));
        return customers.map(customerMapper::apply);
    }

    //save
    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = customerMapper.applyToCustomer(customerDTO);
        return customerMapper.apply(customerRepository.save(customer));
    }

    //delete
    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    //find by phone
    @Override
    public CustomerDTO findByPhone(String phone) {
        Optional<Customer> dto = customerRepository.findByPhone(phone);
        return dto.map(customerMapper::apply).orElse(null);
    }
}
