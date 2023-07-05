package com.example.googlevue11_server.messaging;



import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilioMessagingService implements MessagingService {
    private static final String ACCOUNT_SID = "YOUR_TWILIO_ACCOUNT_SID";
    private static final String AUTH_TOKEN = "YOUR_TWILIO_AUTH_TOKEN";
    private static final String FROM_PHONE_NUMBER = "YOUR_TWILIO_PHONE_NUMBER";

    public TwilioMessagingService() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    @Override
    public void sendMessage(String phoneNumber, String message) {
        Message.creator(new PhoneNumber(phoneNumber), new PhoneNumber(FROM_PHONE_NUMBER), message).create();
    }
}
