package com.sti.cmart.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class EWalletDTO {
    private Long id;
    private String identifier;
    private Double money;
    private String Bank;
    private Short status;
    private Long customerId;
}
