package com.example.googlevue11_server.service;



import com.example.googlevue11_server.models.User;
import com.example.googlevue11_server.repository.UserRepository;
import com.example.googlevue11_server.notifications.LoginNeedsVerification;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findOrCreateUser(String phoneNumber) {
        // Check if the user already exists
        User user = userRepository.findByPhoneNumber(phoneNumber);

        // If the user does not exist, create a new user
        if (user == null) {
            user = new User();
            user.setPhoneNumber(phoneNumber);
            // Save the new user to the database
            userRepository.save(user);
        }

        return user;
    }

    public User findUserByPhoneAndLoginCode(String phoneNumber, int loginCode) {
        // Find the user by phone number and login code
        return userRepository.findByPhoneNumberAndLoginCode(phoneNumber, loginCode);
    }

    public void saveUser(User user) {
        // Save the user to the database
        userRepository.save(user);
    }

//    public String generateAuthToken(User user) {
//        // Generate an authentication token for the user (implementation specific)
//        // This method should return the generated authentication token
//        return "GeneratedAuthToken";
//    }

    public String generateAuthToken(User user) {
        // Generate an authentication token for the user
        // This method should generate a unique authentication token for the user
        // and associate it with the user in a secure manner, such as using JWT (JSON Web Tokens)
        // The token can be used for subsequent authentication and authorization purposes
        // You can use libraries like io.jsonwebtoken.Jwts to generate JWT tokens

        // Example implementation:
        String secretKey = "1234"; // Replace with your own secret key
        String token = Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Token expires in 24 hours
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return token;
    }


    public void sendNotification(User user, LoginNeedsVerification notification) {
        // Send the notification to the user (implementation specific)
        // This method should send the notification to the user
        // For example, you can use Twilio or any other messaging service here
        // You will need to implement the logic for sending the actual notification
        // using the provided notification object and the user's phone number
        // Example:
        String phoneNumber = user.getPhoneNumber();
        String message = notification.getMessage();
        // Implement logic to send the notification using Twilio or any other messaging service
        // twilioMessagingService.sendMessage(phoneNumber, message);
        System.out.println("Sending notification to " + phoneNumber + ": " + message);
    }

    public User getUserById(String id) {
        // Retrieve the user from the database using the provided ID
        Optional<User> optionalUser = userRepository.findById(Long.valueOf(id));

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }

        return null; // User not found
    }

}


