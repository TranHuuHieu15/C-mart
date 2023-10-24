package com.sti.cmart.service.impl;

import com.sti.cmart.entity.Voucher;
import com.sti.cmart.model.dto.VoucherDTO;
import com.sti.cmart.model.mapper.VoucherMapper;
import com.sti.cmart.repository.VoucherRepository;
import com.sti.cmart.service.VoucherService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoucherServiceImpl implements VoucherService {

    private final VoucherRepository voucherRepository;
    private final VoucherMapper voucherMapper;

    //findById
    @Override
    public VoucherDTO findById(Long id) {
        Optional<Voucher> voucher = voucherRepository.findById(id);
        return voucher.map(voucherMapper::apply).orElse(null);
    }

    //findAll
    @Override
    public Page<VoucherDTO> findAll(SearchCriteria searchCriteria) {
        Page<Voucher> list = voucherRepository.findAll(Search.getPageable(searchCriteria));
        return list.map(voucherMapper::apply);
    }

    //findByName
    @Override
    public VoucherDTO findByName(String name) {
        Optional<Voucher> voucher = voucherRepository.findByName(name);
        return voucher.map(voucherMapper::apply).orElse(null);
    }

    //save
    @Override
    public VoucherDTO save(VoucherDTO voucherDTO) {
        Voucher voucher = voucherMapper.applyToVoucher(voucherDTO);
        voucher = voucherRepository.save(voucher);
        return voucherMapper.apply(voucher);
    }

    //delete
    @Override
    public void delete(Long id) {
        voucherRepository.deleteById(id);
    }

}
