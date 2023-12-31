package com.sti.cmart.other.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String fullname;
    private String email;
    private String password;
    private String phone;
//    private String accessToken;
//    private String refreshToken;
}
