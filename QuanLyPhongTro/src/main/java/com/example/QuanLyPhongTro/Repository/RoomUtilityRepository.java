package com.example.QuanLyPhongTro.Repository;

import com.example.QuanLyPhongTro.Entity.RoomUtility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomUtilityRepository extends JpaRepository<RoomUtility, Integer> {
}