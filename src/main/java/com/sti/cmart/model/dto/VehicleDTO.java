package com.sti.cmart.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class VehicleDTO {
    private Long id;
    private String name;
    private String licensePlate;
    private String color;
    private String brand;
    private Date yearOfManufacture;
    private String description;
    private Long vehicleTypeId;
    private Long accountId;
}
