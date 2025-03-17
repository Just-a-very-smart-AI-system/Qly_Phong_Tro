package com.example.QuanLyPhongTro.Repository;

import com.example.QuanLyPhongTro.Entity.FavoriteRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRoomRepository extends JpaRepository<FavoriteRoom, Integer> {
}
