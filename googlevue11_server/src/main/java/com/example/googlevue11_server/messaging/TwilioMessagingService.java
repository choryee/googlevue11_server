package com.example.googlevue11_server.messaging;


import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.math.BigDecimal;

@Service
public class TwilioMessagingService {
    private static final String ACCOUNT_SID = "ACc38d3a007a1fca976f03152b65ca2356";
    private static final String AUTH_TOKEN = "159b15d9cd67269aeb0533fc0d1d68dd";
    private static final String FROM_PHONE_NUMBER = "+16183864018";

    public TwilioMessagingService() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);    }


    public static void sendMessage(String phoneNumber, String message) {
        Message.creator(new PhoneNumber(phoneNumber), new PhoneNumber(FROM_PHONE_NUMBER), message).create();
    }


}
