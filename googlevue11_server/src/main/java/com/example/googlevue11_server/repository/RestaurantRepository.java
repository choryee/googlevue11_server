package com.example.googlevue11_server.repository;

import com.example.googlevue11_server.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
