package com.sti.cmart.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class TransactionDTO {
    private Long id;
    private Date date;
    private Double money;
    private Short status;
    private Long eWalletId;
}
