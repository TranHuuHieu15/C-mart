package com.sti.cmart.facade;

import com.sti.cmart.service.SupportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupportFacade {
    private final SupportService supportService;
}
