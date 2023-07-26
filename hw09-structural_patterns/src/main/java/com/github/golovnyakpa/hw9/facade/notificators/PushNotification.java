package com.github.golovnyakpa.hw9.facade.notificators;

public class PushNotification {

    public String sendPush(String pushFrom, String pushTo, String pushText) {
        return String.format(
                "Push notification from '%s' app to '%s' user with text: '%s'", pushFrom, pushTo, pushText
        );
    }

}
