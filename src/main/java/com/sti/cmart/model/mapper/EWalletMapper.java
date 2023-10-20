package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.Customer;
import com.sti.cmart.entity.EWallet;
import com.sti.cmart.model.dto.EWalletDTO;
import com.sti.cmart.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class EWalletMapper implements Function<EWallet, EWalletDTO> {

    private final CustomerRepository customerRepository;

    @Override
    public EWalletDTO apply(EWallet eWallet) {
        return EWalletDTO.builder()
                .id(eWallet.getId())
                .identifier(eWallet.getIdentifier())
                .money(eWallet.getMoney())
                .Bank(eWallet.getBank())
                .status(eWallet.getStatus())
                .customerId(eWallet.getCustomer().getId())
                .build();
    }

    public EWallet applyToEWallet(EWalletDTO eWalletDTO) {
        Customer customer = customerRepository.findById(eWalletDTO.getCustomerId()).get();
        return EWallet.builder()
                .id(eWalletDTO.getId())
                .identifier(eWalletDTO.getIdentifier())
                .money(eWalletDTO.getMoney())
                .bank(eWalletDTO.getBank())
                .status(eWalletDTO.getStatus())
                .customer(customer)
                .build();
    }
}
