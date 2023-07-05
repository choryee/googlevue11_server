package com.example.googlevue11_server.repository;

import com.example.googlevue11_server.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, String> {
    // 여기에 필요한 추가적인 메소드 선언이 가능합니다.
}
