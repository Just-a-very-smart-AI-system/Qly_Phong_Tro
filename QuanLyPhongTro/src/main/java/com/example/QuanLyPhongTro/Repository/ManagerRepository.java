package com.example.QuanLyPhongTro.Repository;

import com.example.QuanLyPhongTro.Entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
