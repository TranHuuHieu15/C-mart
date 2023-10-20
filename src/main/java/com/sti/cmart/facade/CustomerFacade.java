package com.sti.cmart.facade;

import com.sti.cmart.entity.Customer;
import com.sti.cmart.exception.common.InvalidParamException;
import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.exception.entity.EntityAlreadyExistException;
import com.sti.cmart.exception.entity.EntityNotFoundException;
import com.sti.cmart.model.dto.CustomerDTO;
import com.sti.cmart.service.CustomerService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerFacade {

    private final CustomerService customerService;

    //find by id
    public CustomerDTO findById(Long id) throws ArchitectureException{
        if (id == null)
            throw new InvalidParamException();
        CustomerDTO dto = customerService.findById(id);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //find by username
    public CustomerDTO findByUsername(String username) throws ArchitectureException{
        if (username == null)
            throw new InvalidParamException();
        CustomerDTO dto = customerService.findByUsername(username);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //find by email
    public CustomerDTO findByEmail(String email) throws ArchitectureException{
        if (email == null)
            throw new InvalidParamException();
        CustomerDTO dto = customerService.findByEmail(email);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //find id exists
    public CustomerDTO checkIdExists(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        CustomerDTO dto = customerService.findById(id);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //save
    public CustomerDTO save(CustomerDTO customerDTO) throws ArchitectureException{
        if (customerDTO == null)
            throw new InvalidParamException();
        CustomerDTO dto = customerService.findById(customerDTO.getId());
        CustomerDTO email = customerService.findByEmail(customerDTO.getEmail());
        CustomerDTO username = customerService.findByUsername(customerDTO.getUsername());
        CustomerDTO phone = customerService.findByPhone(customerDTO.getPhone());
        if (dto != null || email != null || username != null || phone != null)
            throw new EntityAlreadyExistException();
        return customerService.save(customerDTO);
    }

    //update
    public CustomerDTO update(CustomerDTO customerDTO) throws ArchitectureException {
        if (customerDTO == null)
            throw new InvalidParamException();
        checkIdExists(customerDTO.getId());
        CustomerDTO email = customerService.findByEmail(customerDTO.getEmail());
        CustomerDTO username = customerService.findByUsername(customerDTO.getUsername());
        CustomerDTO phone = customerService.findByPhone(customerDTO.getPhone());
        if (email == null || username == null || phone == null)
            throw new EntityAlreadyExistException();
        return customerService.save(customerDTO);
    }

    //delete
    public void delete(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        checkIdExists(id);
        customerService.delete(id);
    }

    //find by phone
    public CustomerDTO findByPhone(String phone) throws ArchitectureException {
        if (phone == null)
            throw new InvalidParamException();
        CustomerDTO dto = customerService.findByPhone(phone);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //find all customer
    public Page<CustomerDTO> findAllCustomer(SearchCriteria searchCriteria) throws ArchitectureException {
        if (searchCriteria == null)
            throw new InvalidParamException();
        Page<CustomerDTO> list = customerService.findAllCustomer(searchCriteria);
        if (list == null)
            throw new EntityNotFoundException();
        return list;
    }

    //find all by fullname
    public Page<CustomerDTO> findAllByFullname(String fullname, SearchCriteria searchCriteria) throws ArchitectureException {
        if (fullname == null || searchCriteria == null)
            throw new InvalidParamException();
        Page<CustomerDTO> list = customerService.findAllByFullname(fullname, searchCriteria);
        if (list == null)
            throw new EntityNotFoundException();
        return list;
    }
}
