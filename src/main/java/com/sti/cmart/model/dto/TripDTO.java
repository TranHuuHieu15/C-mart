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
    private String startLocation; // vị trí bắt đầu
    private String endLocation;// vị trí kết thúc
    private Double distance;// khoảng cách
    private Double price; // giá
    private Short status; // trạng thái
    private Long paymentId; // id thanh toán
    private Date dropTime; // thời gian trả
    private Date pickUpTime; // thời gian đón
//    private Long feedbackTripId;
    private Long serviceId; // id dịch vụ
//    private Long VoucherId;
//    private Long chatId;
    private Long addressId; // id địa chỉ
}
