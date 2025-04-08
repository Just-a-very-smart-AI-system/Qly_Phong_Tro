package com.example.QuanLyPhongTro.Repository;


import com.example.QuanLyPhongTro.DTO.Response.ProvinceResponseDTO;
import com.example.QuanLyPhongTro.Entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    Boolean existsByDistrictId(Integer id);
    Boolean existsByDistrictName(String name);

    List<District> findAllByProvinceProvinceId(Integer id);
}