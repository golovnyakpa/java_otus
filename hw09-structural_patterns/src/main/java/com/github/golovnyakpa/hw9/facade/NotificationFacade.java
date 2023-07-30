package com.github.golovnyakpa.hw9.facade;

import com.github.golovnyakpa.hw9.facade.notificators.EmailNotification;
import com.github.golovnyakpa.hw9.facade.notificators.PushNotification;
import com.github.golovnyakpa.hw9.facade.notificators.SmsNotification;

public class NotificationFacade {

    public String sendNotification(
            NotificationType notificationType,
            String notificationTo,
            String notificationFrom,
            String text) {
        return switch (notificationType) {
            case EMAIL -> new EmailNotification(notificationTo, notificationFrom)
                    .sendEmailNotification(text);
            case PUSH ->
                    new PushNotification().sendPush(notificationFrom, notificationTo, text);
            case SMS ->
                    new SmsNotification(notificationFrom).sendSms(notificationTo, text);
        };
    }
}
