package com.sti.cmart.model.dto;

import com.sti.cmart.entity.Account;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class AccountDTO {
    private Long id;
    private String username;
    private String password;
    private String fullname;
    private String phone;
    private String email;
    private Boolean isActive;
    private String image;
    private Short status;
    private Long roleId;
//    private Long eWalletsId;
//    private Long addressId;
//    private Long vehiclesId;
//    private Long ChatId;
//    private Long MessageId;
}
