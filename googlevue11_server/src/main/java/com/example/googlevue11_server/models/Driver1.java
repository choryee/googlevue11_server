//package com.example.googlevue11_server.models;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.CreationTimestamp;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//public class Driver1 {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @Column(nullable = true, length = 100)
//    private String name;
//
//
//    @Column(nullable = true, length = 100)
//    private User user;
//
//    //@OneToMany(mappedBy = "driver", fetch = FetchType.EAGER)
//    @Column(nullable = true, length = 100)
//    private List<Trip> trip;
//
//    @Column(nullable = true, length = 100)
//    private int year;
//
//    @Column(nullable = true, length = 100)
//    private String make;
//
//    @Column(nullable = true, length = 100)
//    private String model;
//
//    @Column(nullable = true, length = 100)
//    private String color;
//
//    @Column(nullable = true, length = 100)
//    private String licensePlate;
//
//    @CreationTimestamp
//    private Timestamp createDate;
//
//
//    public Driver1(int id, int year, String make, String model, String color, String licensePlate) {
//    }
//}
