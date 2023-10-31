package com.sti.cmart.facade;

import com.sti.cmart.entity.Account;
import com.sti.cmart.exception.common.InvalidParamException;
import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.exception.entity.EntityAlreadyExistException;
import com.sti.cmart.exception.entity.EntityNotFoundException;
import com.sti.cmart.model.dto.AccountDTO;
import com.sti.cmart.other.request.LoginRequest;
import com.sti.cmart.service.AccountService;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountFacade {

    private final AccountService accountService;

    //find by id
    public AccountDTO findById(Long id) throws ArchitectureException{
        if (id == null)
            throw new InvalidParamException();
        AccountDTO dto = accountService.findById(id);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //find by username
    public AccountDTO findByUsername(String username) throws ArchitectureException{
        if (username == null)
            throw new InvalidParamException();
        AccountDTO dto = accountService.findByUsername(username);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //find by email
    public AccountDTO findByEmail(String email) throws ArchitectureException{
        if (email == null)
            throw new InvalidParamException();
        AccountDTO dto = accountService.findByEmail(email);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //find id exists
    public AccountDTO checkIdExists(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        AccountDTO dto = accountService.findById(id);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //save
    public ResponseEntity<?> save(AccountDTO accountDTO) throws ArchitectureException{
        if (accountDTO == null)
            throw new InvalidParamException();
//        AccountDTO dto = accountService.findById(accountDTO.getId());
        AccountDTO email = accountService.findByEmail(accountDTO.getEmail());
        AccountDTO username = accountService.findByUsername(accountDTO.getUsername());
        AccountDTO phone = accountService.findByPhone(accountDTO.getPhone());
        if ( email != null || username != null || phone != null)
            throw new EntityAlreadyExistException();
        return accountService.save(accountDTO);
    }

//    //update
    public AccountDTO update(AccountDTO accountDTO) throws ArchitectureException {
        if (accountDTO == null)
            throw new InvalidParamException();
        checkIdExists(accountDTO.getId());
        AccountDTO email = accountService.findByEmail(accountDTO.getEmail());
        AccountDTO username = accountService.findByUsername(accountDTO.getUsername());
        AccountDTO phone = accountService.findByPhone(accountDTO.getPhone());
        if (email == null || username == null || phone == null)
            throw new EntityAlreadyExistException();
        return accountService.saveDTO(accountDTO);
    }

    //delete
    public void delete(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        checkIdExists(id);
        accountService.delete(id);
    }

    //find by phone
    public AccountDTO findByPhone(String phone) throws ArchitectureException {
        if (phone == null)
            throw new InvalidParamException();
        AccountDTO dto = accountService.findByPhone(phone);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //find all customer
    public Page<AccountDTO> findAllCustomer(SearchCriteria searchCriteria) throws ArchitectureException {
        if (searchCriteria == null)
            throw new InvalidParamException();
        Page<AccountDTO> list = accountService.findAllCustomer(searchCriteria);
        if (list == null)
            throw new EntityNotFoundException();
        return list;
    }

    //find all by fullname
    public Page<AccountDTO> findAllByFullname(String fullname, SearchCriteria searchCriteria) throws ArchitectureException {
        if (fullname == null || searchCriteria == null)
            throw new InvalidParamException();
        Page<AccountDTO> list = accountService.findAllByFullname(fullname, searchCriteria);
        if (list == null)
            throw new EntityNotFoundException();
        return list;
    }

    public ResponseEntity<?> login(LoginRequest loginRequest) throws ArchitectureException{
        if (loginRequest == null)
            throw new InvalidParamException();
        AccountDTO account = accountService.findByUsername(loginRequest.getUsername());
        if (account == null)
            throw new EntityNotFoundException();
        return accountService.login(loginRequest);
    }
}
