package com.example.googlevue11_server.service;

import com.example.googlevue11_server.models.Restaurant;
import com.example.googlevue11_server.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Transactional
    public List<Restaurant> findAll(){
        return restaurantRepository.findAll();
    }



}
