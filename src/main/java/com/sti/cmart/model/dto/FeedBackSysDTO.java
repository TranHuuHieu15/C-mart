package com.sti.cmart.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class FeedBackSysDTO {
    private Long id;
    private String content;
    private Date date;
    private Short status;
    private Long accountId;
    private Long problemId;
}
