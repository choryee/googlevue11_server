package com.example.googlevue11_server.notifications;


import com.example.googlevue11_server.messaging.TwilioMessagingService;

public interface Notification {
    String getMessage();
    void setMessage(String message);
    void sendNotification(String recipient, TwilioMessagingService twilioMessagingService);
}
