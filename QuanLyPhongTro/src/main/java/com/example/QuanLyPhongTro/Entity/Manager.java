package com.example.QuanLyPhongTro.Entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "managers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer managerId;

    @Column(length = 50)
    private String username;

    @Column(length = 255)
    private String password;

    @Column(length = 100)
    private String email;

    @Column(length = 15)
    private String phone;

    @Column(length = 255)
    private String address;

    @Column(length = 20)
    private String cmnd;

    @Column(name = "extra_contact", length = 100)
    private String extraContact;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}