package com.sti.cmart.facade;

import com.sti.cmart.exception.common.InvalidParamException;
import com.sti.cmart.exception.core.ArchitectureException;
import com.sti.cmart.exception.entity.EntityAlreadyExistException;
import com.sti.cmart.exception.entity.EntityNotFoundException;
import com.sti.cmart.model.dto.AddressDTO;
import com.sti.cmart.service.AddressSerivce;
import com.sti.cmart.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressFacade {

    private final AddressSerivce addressSerivce;

    //findById
    public AddressDTO findById(Long id) throws ArchitectureException {
        if (id == null)
            throw new InvalidParamException();
        AddressDTO dto = addressSerivce.findById(id);
        if (dto == null)
            throw new EntityNotFoundException();
        return dto;
    }

    //findAll
    public Page<AddressDTO> findAll(SearchCriteria searchCriteria) throws ArchitectureException {
        Page<AddressDTO> list = addressSerivce.findAll(searchCriteria);
        if (list.isEmpty())
            throw new EntityNotFoundException();
        return list;
    }

    //save
    public AddressDTO save(AddressDTO addressDTO) throws ArchitectureException {
        if (addressDTO == null)
            throw new InvalidParamException();
        AddressDTO dto = addressSerivce.findById(addressDTO.getId());
        if (dto != null)
            throw new EntityAlreadyExistException();
        return addressSerivce.save(addressDTO);
    }

    //update
    public AddressDTO update(AddressDTO addressDTO) throws ArchitectureException {
        if (addressDTO == null)
            throw new InvalidParamException();
        findById(addressDTO.getId());
        return addressSerivce.save(addressDTO);
    }

    //detele
    public void delete(Long id) throws ArchitectureException {
        findById(id);
        addressSerivce.delete(id);
    }
}
