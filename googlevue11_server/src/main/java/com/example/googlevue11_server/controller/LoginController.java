package com.example.googlevue11_server.controller;

import com.example.googlevue11_server.messaging.LoginCodeGenerator;
import com.example.googlevue11_server.messaging.TwilioMessagingService;
import com.example.googlevue11_server.models.User;
import com.example.googlevue11_server.notifications.LoginNeedsVerification;
import com.example.googlevue11_server.service.UserService;
import com.example.googlevue11_server.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    TwilioMessagingService twilioMessagingService;
    @Autowired
    JwtUtil jwtUtil;

    LoginCodeGenerator loginCodeGenerator = new LoginCodeGenerator();

//    @Autowired
//    public LoginController(UserService userService, TwilioMessagingService twilioMessagingService) {
//        this.userService = userService;
//        this.twilioMessagingService = twilioMessagingService;
//    }

    public LoginController() {

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
        LoginNeedsVerification loginNeedsVerification = new LoginNeedsVerification(twilioMessagingService);
        loginNeedsVerification.sendNotification(phoneRequest.phone);
        //user.sendNotification(new LoginNeedsVerification(twilioMessagingService));

        //System.out.println(loginCodeGenerator.generateLoginCode());

        // Return a response
        return ResponseEntity.ok("Text message notification sent. 확인문자 발송됨...");
    }

    String token=null;
    @GetMapping("/submit") //Get
    public ResponseEntity<String> submitLogin(@RequestHeader("Authorization") String authorizationHeader) {
        // Authorization 헤더에서 토큰 추출
        token= jwtUtil.extractToken(authorizationHeader);
        System.out.println(" 받은 token>>"+token); // 토큰 받아옴.


        // 토큰 인증을 수행하는 로직
        if (jwtUtil.authenticateToken(token)) {
            // 토큰이 유효한 경우
           // return ResponseEntity.ok("Authentication successful");
            return ResponseEntity.ok("ok");
        } else {
            // 토큰이 유효하지 않은 경우
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/verify")
    public ResponseEntity<?> verifyCode(@RequestBody VerificationRequest verificationRequest) { //VerificationRequest 밑에 있음.
        // Validate the incoming request
        if (verificationRequest.getPhone() == null || verificationRequest.getPhone().length() < 10) {
            return ResponseEntity.badRequest().body("Invalid verification request.");
        }
        System.out.println("verificationRequest.getLoginCode()>>"+ verificationRequest.getLogin_code());
        System.out.println("verificationRequest.getPhone()>>"+ verificationRequest.getPhone());
        // Find the user
        User user = userService.findUserByPhone(verificationRequest.getPhone());

        // Check if the provided code is valid
        if (user != null) {// user있으면,
            // Clear the login code
            System.out.println("user>>"+ user);
            user.setLoginCode(verificationRequest.getLogin_code());
            userService.saveUser(user);

            // Return an auth token
            String authToken = jwtUtil.generateToken(user);
            // user.setRememberToken(authToken); 저장 안됨.

            System.out.println("authToken>>"+authToken);
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
        private String login_code;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getLogin_code() {
            return login_code;
        }

        public void setLogin_code(String login_code) {
            this.login_code = login_code;
        }
    }

}
