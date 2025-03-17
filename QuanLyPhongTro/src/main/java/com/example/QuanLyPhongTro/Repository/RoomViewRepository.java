package com.example.QuanLyPhongTro.Repository;

import com.example.QuanLyPhongTro.Entity.RoomView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomViewRepository extends JpaRepository<RoomView, Integer> {
}