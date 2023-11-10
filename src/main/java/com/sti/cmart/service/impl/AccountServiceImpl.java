package com.sti.cmart.service.impl;

import com.sti.cmart.entity.Account;
import com.sti.cmart.entity.Role;
import com.sti.cmart.model.dto.AccountDTO;
import com.sti.cmart.model.mapper.AccountMapper;
import com.sti.cmart.repository.AccountRepository;
import com.sti.cmart.repository.RoleRepository;
import com.sti.cmart.service.AccountService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final RoleRepository roleRepository;

    //find by id
    @Override
    public AccountDTO findById(Long id) {
        Optional<Account> dto = accountRepository.findById(id);
        return dto.map(accountMapper::apply).orElse(null);
    }

    //find by username
    @Override
    public AccountDTO findByUsername(String username) {
        Optional<Account> dto = accountRepository.findByUsername(username);
        return dto.map(accountMapper::apply).orElse(null);
    }

    //find by email
    @Override
    public AccountDTO findByEmail(String email) {
        Optional<Account> dto = accountRepository.findByEmail(email);
        return dto.map(accountMapper::apply).orElse(null);
    }

    //find all by fullname
    @Override
    public Page<AccountDTO> findAllByFullname(String fullname, SearchCriteria searchCriteria) {
        Page<Account> customers = accountRepository.findAllByFullname(fullname, Search.getPageable(searchCriteria));
        return customers.map(accountMapper::apply);
    }

    //find all customer
    @Override
    public Page<AccountDTO> findAllCustomer(SearchCriteria searchCriteria) {
        Page<Account> customers = accountRepository.findAll(Search.getPageable(searchCriteria));
        return customers.map(accountMapper::apply);
    }

    //delete
    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    //find by phone
    @Override
    public AccountDTO findByPhone(String phone) {
        Optional<Account> dto = accountRepository.findByPhone(phone);
        return dto.map(accountMapper::apply).orElse(null);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Account saverUser(Account user) {
        return accountRepository.save(user);
    }

    @Override
    public AccountDTO saveDTO(AccountDTO dto) {
        Account account = accountMapper.applyToCustomer(dto);
        account = accountRepository.save(account);
        return accountMapper.apply(account);
    }
}
