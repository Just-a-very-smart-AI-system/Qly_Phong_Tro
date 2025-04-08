package com.example.QuanLyPhongTro.Repository;

import com.example.QuanLyPhongTro.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    // Tìm kiếm phòng theo provinceName
    @Query("SELECT r FROM Room r JOIN r.address a JOIN a.ward w JOIN w.district d JOIN d.province p WHERE p.provinceName = :provinceName")
    List<Room> findByProvinceName(String provinceName);

    // Tìm kiếm phòng theo districtName
    @Query("SELECT r FROM Room r JOIN r.address a JOIN a.ward w JOIN w.district d WHERE d.districtName = :districtName")
    List<Room> findByDistrictName(String districtName);

    // Tìm kiếm phòng theo districtId
    @Query("SELECT r FROM Room r JOIN r.address a JOIN a.ward w JOIN w.district d WHERE d.districtId = :districtId")
    List<Room> findByDistrictId(Integer districtId);

    // Tìm kiếm phòng theo provinceId
    @Query("SELECT r FROM Room r JOIN r.address a JOIN a.ward w JOIN w.district d JOIN d.province p WHERE p.provinceId = :provinceId")
    List<Room> findByProvinceId(Integer provinceId);
    @Query("SELECT r FROM Room r JOIN r.address a JOIN a.ward w WHERE w.wardId = :wardId")
    List<Room> findByWardId(Integer wardId);
    @Query("SELECT r FROM Room r JOIN r.address a JOIN a.ward w WHERE w.wardName = :wardName")
    List<Room> findByWardName(String wardName);
    // Tìm kiếm phòng theo trạng thái isAvailable
    List<Room> findByIsActive(Integer isActive);
    @Query("SELECT r FROM Room r WHERE r.price BETWEEN :minPrice AND :maxPrice")
    List<Room> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<Room> findByManagerManagerId(Integer id);
}