package com.example.QuanLyPhongTro.Repository;

import com.example.QuanLyPhongTro.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query("SELECT a FROM Address a " +
            "JOIN a.ward w " +
            "JOIN w.district d " +
            "JOIN d.province p " +
            "WHERE LOWER(p.provinceName) LIKE LOWER(CONCAT('%', :provinceName, '%'))")
    List<Address> findByProvinceName(String provinceName);

    @Query("SELECT a FROM Address a " +
            "JOIN a.ward w " +
            "JOIN w.district d " +
            "WHERE LOWER(d.districtName) LIKE LOWER(CONCAT('%', :districtName, '%'))")
    List<Address> findByDistrictName(String districtName);

    @Query("SELECT a FROM Address a " +
            "JOIN a.ward w " +
            "JOIN w.district d " +
            "WHERE d.districtId = :districtId")
    List<Address> findByDistrictId(Integer districtId);

    @Query("SELECT a FROM Address a " +
            "JOIN a.ward w " +
            "JOIN w.district d " +
            "JOIN d.province p " +
            "WHERE p.provinceId = :provinceId")
    List<Address> findByProvinceId(Integer provinceId);
}