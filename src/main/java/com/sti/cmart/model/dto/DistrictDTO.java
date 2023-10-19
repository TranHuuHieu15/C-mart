package com.sti.cmart.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class DistrictDTO {
    private Long id;
    private String name;
    private String location;
    private Long provinceId;
}
