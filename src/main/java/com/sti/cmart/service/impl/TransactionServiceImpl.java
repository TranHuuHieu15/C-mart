package com.sti.cmart.service.impl;

import com.sti.cmart.entity.Transaction;
import com.sti.cmart.model.dto.TransactionDTO;
import com.sti.cmart.model.mapper.TransactionMapper;
import com.sti.cmart.repository.TransactionRepository;
import com.sti.cmart.service.TransactionService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    //findById
    @Override
    public TransactionDTO findById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.map(transactionMapper::apply).orElse(null);
    }
    //findAll
    @Override
    public Page<TransactionDTO> findAll(SearchCriteria searchCriteria) {
        Page<Transaction> transactions = transactionRepository.findAll(Search.getPageable(searchCriteria));
        return transactions.map(transactionMapper::apply);
    }
    //save
    @Override
    public TransactionDTO save(TransactionDTO transactionDTO) {
        Transaction transaction = transactionMapper.applyToTransaction(transactionDTO);
        return transactionMapper.apply(transactionRepository.save(transaction));
    }
    //delete
    @Override
    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }

}
