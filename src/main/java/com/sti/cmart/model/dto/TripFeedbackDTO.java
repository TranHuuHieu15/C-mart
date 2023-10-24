package com.sti.cmart.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class TripFeedbackDTO {
    private Long id;
    private Long tripId;
    private String content;
    private Short star;
    private Short attitude;
    private Short safety;
    private Short speed;
    private Short efficiency;
}
