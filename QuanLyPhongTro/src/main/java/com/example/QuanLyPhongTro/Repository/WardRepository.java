package com.example.QuanLyPhongTro.Repository;

import com.example.QuanLyPhongTro.Entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends JpaRepository<Ward, Integer> {
    boolean existsByWardId(Integer id);
}