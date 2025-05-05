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

    @Column(name = "username", length = 50)
    private String userName;

    @Column(name = "password",length = 255)
    private String password;

    @Column(name = "email",length = 100)
    private String email;

    @Column(name = "phone",length = 15)
    private String phone;

    @Column(name = "address",length = 255)
    private String address;

    @Column(name = "cmnd",length = 20)
    private String cmnd;

    @Column(name = "extra_contact", length = 100)
    private String extraContact;

    @Column(name = "role", length = 50)
    private String role;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}