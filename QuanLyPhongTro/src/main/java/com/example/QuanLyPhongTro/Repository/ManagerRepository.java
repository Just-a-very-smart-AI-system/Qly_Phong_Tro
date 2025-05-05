package com.example.QuanLyPhongTro.Repository;

import com.example.QuanLyPhongTro.Entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    Optional<Manager> findByUserName(String username);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
