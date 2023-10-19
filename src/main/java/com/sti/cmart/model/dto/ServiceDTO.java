package com.sti.cmart.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class ServiceDTO {
    private Long id;
    private String name;
    private String poster;
    private Double pricePerKm;
    private Double minPrice;
    private Long vehicleTypeId;
    private Double minKmRequire;
    private String description;
}
