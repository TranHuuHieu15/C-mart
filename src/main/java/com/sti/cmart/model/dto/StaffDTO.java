package com.sti.cmart.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class StaffDTO {
    private Long id;
    private String fullname;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Boolean isActive;
    private String address;
    private String image;
    private Short status;
    private Long adminId;
}
