package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.Account;
import com.sti.cmart.entity.Role;
import com.sti.cmart.entity.RoleName;
import com.sti.cmart.model.dto.AccountDTO;
import com.sti.cmart.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountMapper implements Function<Account, AccountDTO> {

    private final RoleRepository roleRepository;

    @Override
    public AccountDTO apply(Account account) {
        return AccountDTO.builder()
//                .id(account.getId())
                .username(account.getUsername())
                .password(account.getPassword())
                .fullname(account.getFullname())
                .phone(account.getPhone())
                .email(account.getEmail())
                .isActive(account.getIsActive())
                .image(account.getImage())
                .status(account.getStatus())
//                .roleId(account.getId()) // Lấy account đầu tiên
                .build();
    }

    public Account applyToCustomer(AccountDTO accountDTO) {
            return Account.builder()
                    .id(accountDTO.getId())
                    .username(accountDTO.getUsername())
                    .password(accountDTO.getPassword())
                    .fullname(accountDTO.getFullname())
                    .phone(accountDTO.getPhone())
                    .email(accountDTO.getEmail())
                    .isActive(accountDTO.getIsActive())
                    .image(accountDTO.getImage())
                    .status(accountDTO.getStatus())
                    .build();
        }
}
