package com.example.googlevue11_server.models;

import com.example.googlevue11_server.notifications.LoginNeedsVerification;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true, length = 100)
    private String name; // 아이디

    @Column(nullable = true, length = 100)
    private Driver driver;

    @Column(nullable = true, length = 100)
    private String phoneNumber;

    @Column(nullable = true, length = 100)
    private String loginCode;

    //@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Column(nullable = true, length = 100)
    private List<Trip> trip;


    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate createDate; // 날짜

    @PrePersist // DB에 INSERT 되기 직전에 실행. 즉 DB에 값을 넣으면 자동으로 실행됨
    public void createDate() {
        this.createDate = LocalDate.now();
    }

    // Send the user a one-time use code
    public void sendNotification(LoginNeedsVerification loginNeedsVerification) {
    }

//    public void setLoginCode(Object o) {
//        this.login_code=(String) o;
//    }


}
