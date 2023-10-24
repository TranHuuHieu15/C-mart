package com.sti.cmart.facade;

import com.sti.cmart.exception.common.InvalidParamException;
import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.exception.entity.EntityAlreadyExistException;
import com.sti.cmart.exception.entity.EntityNotFoundException;
import com.sti.cmart.model.dto.PaymentDTO;
import com.sti.cmart.service.PaymentService;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentFacade {

    private final PaymentService paymentService;

    //findById
    public PaymentDTO findById(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        PaymentDTO dto = paymentService.findById(id);
        if (id == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //findAll
    public Page<PaymentDTO> findAll(SearchCriteria searchCriteria) throws ArchitectureException {
        Page<PaymentDTO> list = paymentService.findAll(searchCriteria);
        if (list.isEmpty())
            throw new EntityNotFoundException();
        return list;
    }

    //save
    public PaymentDTO save(PaymentDTO paymentDTO) throws ArchitectureException {
        if (paymentDTO == null)
            throw new InvalidParamException();
        PaymentDTO check = paymentService.findById(paymentDTO.getId());
        if (check != null)
            throw new EntityAlreadyExistException();
        return paymentService.save(paymentDTO);
    }

    //update
    public PaymentDTO update(PaymentDTO paymentDTO) throws ArchitectureException {
        if (paymentDTO == null)
            throw new InvalidParamException();
        findById(paymentDTO.getId());
        return paymentService.save(paymentDTO);
    }

    //delete
    public void delete(Long id) throws ArchitectureException {
        findById(id);
        paymentService.delete(id);
    }
}
