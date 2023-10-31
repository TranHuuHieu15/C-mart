package com.sti.cmart.model.mapper;

import com.sti.cmart.entity.FeedbackSy;
import com.sti.cmart.model.dto.FeedBackSysDTO;
import com.sti.cmart.repository.AccountRepository;
import com.sti.cmart.repository.SupportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class FeedBackSysMapper implements Function<FeedbackSy, FeedBackSysDTO> {

    private final AccountRepository accountRepository;
    private final SupportRepository supportRepository;

    @Override
    public FeedBackSysDTO apply(FeedbackSy feedbackSy) {
        return FeedBackSysDTO.builder()
                .id(feedbackSy.getId())
                .date(feedbackSy.getDate())
                .content(feedbackSy.getContent())
                .status(feedbackSy.getStatus())
                .accountId(feedbackSy.getAccounts().getId())
                .problemId(feedbackSy.getSupport().getId())
                .build();
    }

    public FeedbackSy applyToFeebackSys(FeedBackSysDTO dto) {
        return FeedbackSy.builder()
                .id(dto.getId())
                .date(dto.getDate())
                .content(dto.getContent())
                .status(dto.getStatus())
                .accounts(accountRepository.findById(dto.getAccountId()).get())
                .support(supportRepository.findById(dto.getProblemId()).get())
                .build();
    }
}
