package com.sti.cmart.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class SupportDTO {
    private Long id;
    private String name;
    private String phone;
    private String description;
}
