package com.example.QuanLyPhongTro.Repository;


import com.example.QuanLyPhongTro.Entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    boolean existsByProvinceId(Integer id);
    boolean existsByProvinceName(String name);
}