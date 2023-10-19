package com.sti.cmart.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class AddressDTO {
    private Long id;
    private String position;
    private String description;
    private Long wardId;
}
