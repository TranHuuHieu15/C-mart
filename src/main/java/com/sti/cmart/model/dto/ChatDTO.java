package com.sti.cmart.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class ChatDTO {
    private Long id;
    private Long customerId;
    private Long driverId;
    private Long tripId;
    private String content;
    private Date date;
}
