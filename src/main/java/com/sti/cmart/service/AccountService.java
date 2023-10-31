package com.sti.cmart.service;

import com.sti.cmart.entity.Account;
import com.sti.cmart.entity.Role;
import com.sti.cmart.model.dto.AccountDTO;
import com.sti.cmart.other.request.LoginRequest;
import com.sti.cmart.util.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    //find by id
    AccountDTO findById(Long id);

    //find by username
    AccountDTO findByUsername(String username);

    //find by email
    AccountDTO findByEmail(String email);

    //find all by fullname
    Page<AccountDTO> findAllByFullname(String fullname, SearchCriteria searchCriteria);

    //find all customer
    Page<AccountDTO> findAllCustomer(SearchCriteria searchCriteria);

    //delete
    void delete(Long id);

    //find by phone
    AccountDTO findByPhone(String phone);

    ResponseEntity<?> save(AccountDTO registerDto);

    Role saveRole(Role role);

    Account saverUser(Account user);

    ResponseEntity<?> login(LoginRequest loginRequest);

    AccountDTO saveDTO(AccountDTO dto);
}
