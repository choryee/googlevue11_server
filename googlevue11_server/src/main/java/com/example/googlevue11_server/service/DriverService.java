package com.example.googlevue11_server.service;



import com.example.googlevue11_server.models.Driver;
import com.example.googlevue11_server.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver getDriverByUserId(Long userId) {
        // Implement logic to get a driver by the user ID from the database
        return driverRepository.findByUserId(userId).orElse(null);
    }

    public void saveDriver(Driver driver) {
        // Implement logic to save a driver to the database
        driverRepository.save(driver);
    }

    // Other methods for driver management
}
