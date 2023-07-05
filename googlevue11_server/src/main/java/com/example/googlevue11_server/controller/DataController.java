package com.example.googlevue11_server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @GetMapping("/")
    public String home(){
        return "data 준비중...";
    }
}
