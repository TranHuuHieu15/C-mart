package com.sti.cmart.service.impl;

import com.sti.cmart.entity.EWallet;
import com.sti.cmart.model.dto.EWalletDTO;
import com.sti.cmart.model.mapper.EWalletMapper;
import com.sti.cmart.repository.EWalletRepository;
import com.sti.cmart.service.EWalletService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EWalletServiceImpl implements EWalletService {

    private final EWalletRepository eWalletRepository;
    private final EWalletMapper eWalletMapper;

    // find by id
    @Override
    public EWalletDTO findById(Long id) {
        Optional<EWallet> dto = eWalletRepository.findById(id);
        return dto.map(eWalletMapper::apply).orElse(null);
    }

    // find by bank
    @Override
    public Page<EWalletDTO> findAllByBank(String bank, SearchCriteria searchCriteria) {
        Page<EWallet> eWallets = eWalletRepository.findAllByBank(bank, Search.getPageable(searchCriteria));
        return eWallets.map(eWalletMapper::apply);
    }

    //findAll
    @Override
    public Page<EWalletDTO> findAll(SearchCriteria searchCriteria) {
        Page<EWallet> eWallets = eWalletRepository.findAll(Search.getPageable(searchCriteria));
        return eWallets.map(eWalletMapper::apply);
    }

    //findAllByStatus
    @Override
    public Page<EWalletDTO> findAllByStatus(Short status, SearchCriteria searchCriteria) {
        Page<EWallet> eWallets = eWalletRepository.findAllByStatus(status, Search.getPageable(searchCriteria));
        return eWallets.map(eWalletMapper::apply);
    }

    //save
    @Override
    public EWalletDTO save(EWalletDTO eWalletDTO) {
        EWallet eWallet = eWalletMapper.applyToEWallet(eWalletDTO);
        return eWalletMapper.apply(eWalletRepository.save(eWallet));
    }

    //delete
    @Override
    public void delete(Long id) {
        eWalletRepository.deleteById(id);
    }

}
