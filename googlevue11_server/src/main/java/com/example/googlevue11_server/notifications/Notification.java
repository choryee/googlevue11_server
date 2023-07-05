package com.example.googlevue11_server.notifications;


public interface Notification {
    String getMessage();
    void setMessage(String message);
    void sendNotification(String recipient);
}
