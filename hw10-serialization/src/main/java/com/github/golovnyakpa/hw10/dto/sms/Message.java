package com.github.golovnyakpa.hw10.dto.sms;

import lombok.Getter;

import java.util.Date;

public class Message {
    private long rowId;
    private String attributedBody;
    @Getter
    private String belongNumber;
    private long date;
    private long dateRead;
    private String guid;
    private long handleId;
    private int hasDdResults;
    private int isDeleted;
    private int isFromMe;
    @Getter
    private Date sendDate;
    private int sendStatus;
    private String service;
    @Getter
    private String text;
}
