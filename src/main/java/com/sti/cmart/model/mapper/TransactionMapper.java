package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.EWallet;
import com.sti.cmart.entity.Transaction;
import com.sti.cmart.model.dto.TransactionDTO;
import com.sti.cmart.repository.EWalletRepository;
import com.sti.cmart.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionCallback;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TransactionMapper implements Function<Transaction, TransactionDTO> {

    private final EWalletRepository eWalletRepository;

    @Override
    public TransactionDTO apply(Transaction transaction) {
        return TransactionDTO.builder()
                .id(transaction.getId())
                .date(transaction.getDate())
                .eWalletId(transaction.getEWallet().getId())
                .money(transaction.getMoney())
                .build();
    }

    public Transaction applyToTransaction(TransactionDTO transactionDTO) {
        EWallet eWallet = eWalletRepository.findById(transactionDTO.getEWalletId()).get();
        return Transaction.builder()
                .id(transactionDTO.getId())
                .date(transactionDTO.getDate())
                .money(transactionDTO.getMoney())
                .eWallet(eWallet)
                .build();
    }
}
