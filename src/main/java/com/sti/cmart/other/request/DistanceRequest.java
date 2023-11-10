package com.sti.cmart.other.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DistanceRequest {
    @NonNull
    private Double startLat;
    @NonNull
    private Double startLng;
    @NonNull
    private Double finishLat;
    @NonNull
    private Double finishLng;
}
