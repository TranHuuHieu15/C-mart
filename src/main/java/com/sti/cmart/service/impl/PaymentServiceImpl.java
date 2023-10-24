package com.sti.cmart.service.impl;

import com.sti.cmart.entity.Payment;
import com.sti.cmart.model.dto.PaymentDTO;
import com.sti.cmart.model.mapper.PaymentMapper;
import com.sti.cmart.repository.PaymentRepository;
import com.sti.cmart.service.PaymentService;
import com.sti.cmart.util.Search;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    //findById
    @Override
    public PaymentDTO findById(Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        return payment.map(paymentMapper::apply).orElse(null);
    }

    //findAll
    @Override
    public Page<PaymentDTO> findAll(SearchCriteria searchCriteria) {
        Page<Payment> list = paymentRepository.findAll(Search.getPageable(searchCriteria));
        return list.map(paymentMapper::apply);
    }

    //save
    @Override
    public PaymentDTO save(PaymentDTO paymentDTO) {
        Payment payment = paymentMapper.applyToPayment(paymentDTO);
        return paymentMapper.apply(paymentRepository.save(payment));
    }

    //delete
    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }

    //findByName
    @Override
    public PaymentDTO findByName(String name) {
        Optional<Payment> payment = paymentRepository.findByName(name);
        return payment.map(paymentMapper::apply).orElse(null);
    }
}
