package com.example.googlevue11_server.notifications;

public class LoginNeedsVerification implements Notification {

    private String message;

    public LoginNeedsVerification() {
        this.message = "Please verify your login using the provided code.";
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void sendNotification(String recipient) {
        // Implementation for sending verification notification
        System.out.println("Sending verification notification to: " + recipient);
        System.out.println("Notification message: " + message);
        // Additional logic to send the notification using Twilio, email, or any other method
    }
}
