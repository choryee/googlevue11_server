package com.example.googlevue11_server.controller;

import com.example.googlevue11_server.models.Restaurant;
import com.example.googlevue11_server.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequiredArgsConstructor
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/index")
    public List<Restaurant> findAll(){
       // System.out.println("restaurantService.findAll()>>"+restaurantService.findAll());
      return restaurantService.findAll();
    }

    @GetMapping("/test")
    public String findTest(){
        return "test OK!!";
    }
}
