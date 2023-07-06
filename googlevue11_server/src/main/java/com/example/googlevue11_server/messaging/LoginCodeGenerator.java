package com.example.googlevue11_server.messaging;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class LoginCodeGenerator {
    private static final int CODE_LENGTH = 6;

    public String generateLoginCode() {
        // Define the characters allowed in the code
        String allowedCharacters = "0123456789";

        // Create a StringBuilder to store the generated code
        StringBuilder codeBuilder = new StringBuilder();

        // Create a random number generator
        Random random = new Random();

        // Generate the code by randomly selecting characters
        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(allowedCharacters.length());
            char randomChar = allowedCharacters.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }

        // Return the generated code as a string
        return codeBuilder.toString();
    }
}
