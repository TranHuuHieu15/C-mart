package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.Support;
import com.sti.cmart.model.dto.SupportDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class SupportMapper implements Function<Support, SupportDTO> {
    @Override
    public SupportDTO apply(Support support) {
        return SupportDTO.builder()
                .id(support.getId())
                .name(support.getName())
                .phone(support.getPhone())
                .description(support.getDescription())
                .build();
    }

    public Support applyToSupport(SupportDTO supportDTO) {
        return Support.builder()
                .id(supportDTO.getId())
                .name(supportDTO.getName())
                .phone(supportDTO.getPhone())
                .description(supportDTO.getDescription())
                .build();
    }
}
