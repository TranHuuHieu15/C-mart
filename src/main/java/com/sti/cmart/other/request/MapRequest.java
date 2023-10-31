package com.sti.cmart.other.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MapRequest {
    private Double originLat;
    private Double originLng;
    private Double destLat;
    private Double destLng;
}
