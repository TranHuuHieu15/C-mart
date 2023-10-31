package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.Account;
import com.sti.cmart.entity.EWallet;
import com.sti.cmart.model.dto.AccountDTO;
import com.sti.cmart.model.dto.EWalletDTO;
import com.sti.cmart.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class EWalletMapper implements Function<EWallet, EWalletDTO> {

    private final AccountRepository accountRepository;

    @Override
    public EWalletDTO apply(EWallet eWallet) {
        return EWalletDTO.builder()
                .id(eWallet.getId())
                .identifier(eWallet.getIdentifier())
                .money(eWallet.getMoney())
                .Bank(eWallet.getBank())
                .status(eWallet.getStatus())
                .customerId(eWallet.getId())
                .build();
    }

    public EWallet applyToEWallet(EWalletDTO eWalletDTO) {
        Account account = accountRepository.findById(eWalletDTO.getCustomerId()).get();
        return EWallet.builder()
                .id(eWalletDTO.getId())
                .identifier(eWalletDTO.getIdentifier())
                .money(eWalletDTO.getMoney())
                .bank(eWalletDTO.getBank())
                .status(eWalletDTO.getStatus())
                .account(account)
                .build();
    }
}
