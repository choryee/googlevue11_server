package com.example.googlevue11_server.controller;

import com.example.googlevue11_server.messaging.TwilioMessagingService;
import com.example.googlevue11_server.models.User;
import com.example.googlevue11_server.notifications.LoginNeedsVerification;
import com.example.googlevue11_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    TwilioMessagingService twilioMessagingService;

//    @Autowired
//    public LoginController(UserService userService, TwilioMessagingService twilioMessagingService) {
//        this.userService = userService;
//        this.twilioMessagingService = twilioMessagingService;
//    }

    public LoginController(){

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/submit")
    public ResponseEntity<?> submitPhoneNumber(@Validated @RequestBody PhoneRequest phoneRequest) { //PhoneRequest밑에 있음.
        // Validate the phone number
        if (phoneRequest.getPhone() == null || phoneRequest.getPhone().length() < 10) {
            return ResponseEntity.badRequest().body("Invalid phone number입니다");
        }
        System.out.println(phoneRequest.getPhone());

        // Find or create a user model
        User user = userService.findOrCreateUser(phoneRequest.getPhone());

        if (user == null) {
            return ResponseEntity.status(401).body("Could not process a user with that phone number.처리불가");
        }

        // Send the user a one-time use code 일회용 코드 보내기.
        LoginNeedsVerification loginNeedsVerification=new LoginNeedsVerification(twilioMessagingService);
        loginNeedsVerification.sendNotification(phoneRequest.phone);
        //user.sendNotification(new LoginNeedsVerification(twilioMessagingService));

        // Return a response
        return ResponseEntity.ok("Text message notification sent. 확인문자 발송됨...");
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/verify")
    public ResponseEntity<?> verifyCode(@Validated @RequestBody VerificationRequest verificationRequest) { //VerificationRequest 밑에 있음.
        // Validate the incoming request
        if (verificationRequest.getPhone() == null || verificationRequest.getPhone().length() < 10
                || verificationRequest.getLoginCode() < 111111
                || verificationRequest.getLoginCode() > 999999) {
            return ResponseEntity.badRequest().body("Invalid verification request.");
        }

        // Find the user
        User user = userService.findUserByPhoneAndLoginCode(verificationRequest.getPhone(), verificationRequest.getLoginCode());

        // Check if the provided code is valid
        if (user != null) {
            // Clear the login code
            user.setLoginCode(user.getLoginCode());
            userService.saveUser(user);

            // Return an auth token
            String authToken = userService.generateAuthToken(user);
            return ResponseEntity.ok(authToken);
        }

        // Invalid verification code
        return ResponseEntity.status(401).body("Invalid verification code.");
    }

    // Request DTO classes
    static class PhoneRequest {
        private String phone;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

    static class VerificationRequest {
        private String phone;
        private int loginCode;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getLoginCode() {
            return loginCode;
        }

        public void setLoginCode(int loginCode) {
            this.loginCode = loginCode;
        }
    }
}
