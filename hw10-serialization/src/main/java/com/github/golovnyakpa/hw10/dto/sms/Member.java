package com.github.golovnyakpa.hw10.dto.sms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Member {
    private String first;
    private long handleId;
    private String imagePath;
    @Getter
    private String last;
    private String middle;
    private String phoneNumber;
    private String service;
    private String thumbPath;
}
