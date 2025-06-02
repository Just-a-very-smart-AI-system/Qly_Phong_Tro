package com.example.QuanLyPhongTro.Repository;

import com.example.QuanLyPhongTro.Entity.RoomMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomMediaRepository extends JpaRepository<RoomMedia, Integer> {
    List<RoomMedia> findAllByRoomRoomId(Long roomId);
}