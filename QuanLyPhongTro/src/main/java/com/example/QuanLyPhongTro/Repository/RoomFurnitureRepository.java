package com.example.QuanLyPhongTro.Repository;

import com.example.QuanLyPhongTro.Entity.Room;
import com.example.QuanLyPhongTro.Entity.RoomFurniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomFurnitureRepository extends JpaRepository<RoomFurniture, Integer> {
    List<RoomFurniture> findAllByRoom(Room room);
}