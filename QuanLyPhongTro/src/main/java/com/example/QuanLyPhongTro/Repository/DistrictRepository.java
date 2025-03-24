package com.example.QuanLyPhongTro.Repository;


import com.example.QuanLyPhongTro.Entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    Boolean existsByDistrictId(Integer id);
    Boolean existsByDistrictName(String name);
}