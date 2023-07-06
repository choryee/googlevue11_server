package com.example.googlevue11_server.repository;



import com.example.googlevue11_server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByPhoneNumber(String phoneNumber);

    User findByPhoneNumberAndLoginCode(String phoneNumber, String loginCode);

}
