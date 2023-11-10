package com.sti.cmart.other.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TripResponse {
    private String startLocation;
    private String endLocation;
    private Double price;
    private String distance;
    private String duration;
}
