package com.example.googlevue11_server.notifications;

import com.example.googlevue11_server.messaging.LoginCodeGenerator;
import com.example.googlevue11_server.messaging.TwilioMessagingService;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginNeedsVerification  {

    private TwilioMessagingService twilioMessagingService;
    private LoginCodeGenerator loginCodeGenerator;



    public LoginNeedsVerification(TwilioMessagingService twilioMessagingService) {
        this.twilioMessagingService = twilioMessagingService;
        loginCodeGenerator=new LoginCodeGenerator();
       // this.message = "Please verify your login using the provided code.";
    }

    public void sendNotification(String phoneNumber) {
        String loginCode=loginCodeGenerator.generateLoginCode();
        System.out.println("loginCode>>"+ loginCode);

        TwilioMessagingService.sendMessage(phoneNumber, loginCode);
        // Implementation for sending verification notification
        System.out.println("Sending verification notification to : " + phoneNumber);
        System.out.println("Notification message : " + loginCode);
        // Additional logic to send the notification using Twilio, email, or any other method
    }
}
