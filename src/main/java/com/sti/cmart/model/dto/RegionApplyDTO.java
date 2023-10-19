package com.sti.cmart.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class RegionApplyDTO {
    private Long id;
    private Long provinceId;
    private Long serviceId;
}
