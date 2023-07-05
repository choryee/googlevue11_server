package com.example.googlevue11_server.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true, length = 100)
    private String name;

    @Column(nullable = true, length = 100)
    private String address;

    @Column(nullable = true, length = 100)
    private String city;

    @Column(nullable = true, length = 100)
    private String state;

    @Column(nullable = true, length = 100)
    private String hours;

    @Column(nullable = true, length = 100)
    private Double latitude;

    @Column(nullable = true, length = 100)
    private Double longitude;
}
