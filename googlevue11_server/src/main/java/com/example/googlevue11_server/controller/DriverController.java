package com.example.googlevue11_server.controller;


import com.example.googlevue11_server.models.Driver;
import com.example.googlevue11_server.models.User;
import com.example.googlevue11_server.service.DriverService;
import com.example.googlevue11_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final UserService userService;
    private final DriverService driverService;

    @Autowired
    public DriverController(UserService userService, DriverService driverService) {
        this.userService = userService;
        this.driverService = driverService;
    }

    @GetMapping("/show")
    public ResponseEntity<User> showDriver(RequestEntity<Void> requestEntity) {
        User user = userService.getUserById(requestEntity.getHeaders().get("user-id").get(0));
        user.setDriver(driverService.getDriverByUserId(String.valueOf(user.getId())));
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/update")
    public ResponseEntity<User> updateDriver(@Validated @RequestBody UpdateDriverRequest request, // UpdateDriverRequest은 밑에 있음.
                                             RequestEntity<Void> requestEntity) {
        User user = userService.getUserById(requestEntity.getHeaders().get("user-id").get(0));
        Driver driver = driverService.getDriverByUserId(String.valueOf(user.getId()));

        // Update user name
        user.setName(request.getName());
        userService.saveUser(user);

        // Update or create driver
        if (driver != null) {
            driver.setYear(request.getYear());
            driver.setMake(request.getMake());
            driver.setModel(request.getModel());
            driver.setColor(request.getColor());
            driver.setLicensePlate(request.getLicensePlate());
        } else {
            driver = new Driver(user.getId(), request.getYear(), request.getMake(), request.getModel(),
                    request.getColor(), request.getLicensePlate());
        }
        driverService.saveDriver(driver);

        user.setDriver(driver);
        return ResponseEntity.ok(user);
    }

    // Request DTO for updating driver information
    private static class UpdateDriverRequest {
        private int year;
        private String make;
        private String model;
        private String color;
        private String licensePlate;
        private String name;

        // Getters and Setters

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getMake() {
            return make;
        }

        public void setMake(String make) {
            this.make = make;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getLicensePlate() {
            return licensePlate;
        }

        public void setLicensePlate(String licensePlate) {
            this.licensePlate=licensePlate;
        }

        public String getName(){
            return this.name;
        }

        public void setName(String name){
            this.name=name;
        }
    }
}

