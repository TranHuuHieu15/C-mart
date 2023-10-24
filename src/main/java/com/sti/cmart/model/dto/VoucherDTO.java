package com.sti.cmart.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class VoucherDTO {
    private Long id;
    private String name;
    private Double discount;
    private String description;
    private Double minDiscount;
    private Double maxDiscount;
    private Short status;
    private Long tripId;
}
