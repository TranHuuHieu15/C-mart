package com.sti.cmart.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class TripDTO {
    private Long id;
    private Long customer;
    private Long driver;
    private Date date;
    private String startLocation;
    private String endLocation;
    private Double distance;
    private Double price;
    private Short status;
    private Long paymentId;
    private Date dropTime;
    private Date pickUpTime;
    private Long feedbackTripId;
    private Long serviceId;
    private Long VoucherId;
    private Long chatId;
    private Long addressId;
}
