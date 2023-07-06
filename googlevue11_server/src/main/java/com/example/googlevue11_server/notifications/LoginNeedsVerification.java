package com.example.googlevue11_server.notifications;

import com.example.googlevue11_server.messaging.TwilioMessagingService;

public class LoginNeedsVerification  {

    private final TwilioMessagingService twilioMessagingService;

    private String message="Please verify your login using the provided code.";

    public LoginNeedsVerification(TwilioMessagingService twilioMessagingService) {
        this.twilioMessagingService = twilioMessagingService;
       // this.message = "Please verify your login using the provided code.";
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public void sendNotification(String phoneNumber) {
        TwilioMessagingService.sendMessage(phoneNumber, message);
        // Implementation for sending verification notification
        System.out.println("Sending verification notification to: " + phoneNumber);
        System.out.println("Notification message: " + message);
        // Additional logic to send the notification using Twilio, email, or any other method
    }
}
