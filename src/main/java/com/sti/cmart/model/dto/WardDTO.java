package com.sti.cmart.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class WardDTO {
    private Long id;
    private String name;
    private Long districtId;
}
