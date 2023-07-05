package com.example.googlevue11_server.repository;



import com.example.googlevue11_server.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByUserId(String userId);
    // 기타 필요한 메소드 정의
}
