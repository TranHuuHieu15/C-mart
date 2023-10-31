package com.sti.cmart.model.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String fullname;
    private String phone;
    private String email;
    private Boolean isActive;
    private String image;
    private Short status;
    private Long roleId;
}
