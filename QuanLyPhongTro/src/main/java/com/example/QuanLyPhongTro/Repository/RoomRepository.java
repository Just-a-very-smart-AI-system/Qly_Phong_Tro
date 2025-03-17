package com.example.QuanLyPhongTro.Repository;

import com.example.QuanLyPhongTro.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
}