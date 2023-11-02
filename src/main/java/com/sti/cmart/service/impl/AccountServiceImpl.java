package com.sti.cmart.service.impl;

import com.sti.cmart.entity.Account;
import com.sti.cmart.entity.Role;
import com.sti.cmart.entity.RoleName;
import com.sti.cmart.model.dto.AccountDTO;
import com.sti.cmart.model.mapper.AccountMapper;
import com.sti.cmart.other.request.LoginRequest;
import com.sti.cmart.other.request.RegisterRequest;
import com.sti.cmart.other.response.AccountResponse;
import com.sti.cmart.repository.AccountRepository;
import com.sti.cmart.repository.RoleRepository;
import com.sti.cmart.service.AccountService;
import com.sti.cmart.service.JwtService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

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
    public ResponseEntity<?> save(AccountDTO registerDto) {
//        Role role = roleRepository.findByRoleName(RoleName.USER).orElseThrow(() -> new EntityNotFoundException("Role not found"));
        Role defaultRole = roleRepository.findByRoleName(RoleName.USER)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy role mặc định"));

        Role selectedRole = registerDto.getRoleId() != null ?
                roleRepository.findById(registerDto.getRoleId())
                        .orElseThrow(() -> new EntityNotFoundException("Role not found")) :
                defaultRole;
        if (accountRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("username is already taken !", HttpStatus.SEE_OTHER);
        } else {
            Account account = Account.builder()
                    .username(registerDto.getUsername())
                    .password(passwordEncoder.encode(registerDto.getPassword()))
                    .fullname(registerDto.getFullname())
                    .phone(registerDto.getPhone())
                    .email(registerDto.getEmail())
                    .isActive(true)
                    .image(registerDto.getImage())
                    .status(registerDto.getStatus())
                    .roles(Collections.singletonList(selectedRole))
                    .build();
            accountRepository.save(account);
            String token = jwtService.generateToken(registerDto.getEmail(), Collections.singletonList(selectedRole.getId()));
            return new ResponseEntity<>(new RegisterRequest(registerDto.getUsername(), registerDto.getEmail(), registerDto.getImage(), token), HttpStatus.OK);
        }
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
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Sai mật khẩu!", HttpStatus.SEE_OTHER);
        }
        Account account = accountRepository.findByUsername(authentication.getName()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        List<Long> rolesNames = new ArrayList<>();
        account.getRoles().forEach(r -> rolesNames.add(r.getId())); // Authority
        String token = jwtService.generateToken(account.getUsername(), rolesNames);
        return new ResponseEntity<>(new AccountResponse(account.getUsername(), account.getEmail(), account.getImage(), token), HttpStatus.OK);
    }

    @Override
    public AccountDTO saveDTO(AccountDTO dto) {
        Account account = accountMapper.applyToCustomer(dto);
        return accountMapper.apply(accountRepository.save(account));
    }
}
