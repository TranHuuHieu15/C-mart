package com.sti.cmart.other.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountResponse {
    private String username;
    private String fullname;
    private String email;
    private String image;
    private String accessToken;
    private String refreshToken;
}
