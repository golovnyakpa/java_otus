package com.github.golovnyakpa.hw9.facade.notificators;

public class EmailNotification {
    private final String to;
    private final String from;

    public EmailNotification(String to, String from) {
        this.to = to;
        this.from = from;
    }

    public String sendEmailNotification(String emailText) {
        return String.format("From: %s\nTo: %s\nText: %s", from, to, emailText);
    }

}
