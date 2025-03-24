package com.example.QuanLyPhongTro.Entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "username", length = 50)
    private String userName;

    @Column(length = 255)
    private String password;

    @Column(length = 100)
    private String email;

    @Column(length = 15)
    private String phone;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}