package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.Payment;
import com.sti.cmart.model.dto.PaymentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class PaymentMapper implements Function<Payment, PaymentDTO> {

    @Override
    public PaymentDTO apply(Payment payment) {
        return PaymentDTO.builder()
                .id(payment.getId())
                .name(payment.getName())
                .build();
    }

    public Payment applyToPayment(PaymentDTO paymentDTO) {
        return Payment.builder()
                .id(paymentDTO.getId())
                .name(paymentDTO.getName())
                .build();
    }

}
