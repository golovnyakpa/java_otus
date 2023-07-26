package com.github.golovnyakpa.hw9.facade.notificators;

public class SmsNotification {

    private final String smsFrom;

    public SmsNotification(String smsFrom) {
        this.smsFrom = smsFrom;
    }

    public String sendSms(String smsTo, String smsText) {
        return String.format("Sending sms from %s to %s with text: %s", smsFrom, smsTo, smsText);
    }

}
