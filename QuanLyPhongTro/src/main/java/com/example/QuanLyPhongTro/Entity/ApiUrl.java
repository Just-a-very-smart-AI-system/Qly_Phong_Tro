package com.example.QuanLyPhongTro.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "api_url")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url", nullable = false)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    public enum Role {
        ROLE_ADMIN, ROLE_MANAGER, ROLE_USER
    }

}