package com.sti.cmart.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class VehicleTypeDTO {
    private Long id;
    private String name;
    private String description;
    private Double rate;
}
