package com.sti.cmart.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class AdminDTO {
    private Long id;
    private String username;
    private String password;
    private String fullname;
    private String phone;
    private String email;
    private Boolean isActive;
    private String address;
    private String image;
    private Short status;
}
