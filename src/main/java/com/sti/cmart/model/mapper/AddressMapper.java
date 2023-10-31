package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.Address;
import com.sti.cmart.entity.Ward;
import com.sti.cmart.model.dto.AddressDTO;
import com.sti.cmart.repository.AccountRepository;
import com.sti.cmart.repository.WardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AddressMapper implements Function<Address, AddressDTO> {

    private final WardRepository wardRepository;
    private final AccountRepository accountRepository;
    @Override
    public AddressDTO apply(Address address) {
        return AddressDTO.builder()
                .id(address.getId())
                .position(address.getPosition())
                .description(address.getDescription())
                .wardId(address.getWard().getId())
                .build();
    }

    public Address applyToAddress(AddressDTO dto) {
        Ward ward = wardRepository.findById(dto.getWardId()).get();
        return Address.builder()
                .id(dto.getId())
                .position(dto.getPosition())
                .description(dto.getDescription())
                .ward(ward)
                .build();
    }
}
