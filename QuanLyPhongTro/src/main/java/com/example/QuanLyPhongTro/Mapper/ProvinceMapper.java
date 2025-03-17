package com.example.QuanLyPhongTro.Mapper;


import com.example.QuanLyPhongTro.DTO.Request.CreateProvinceRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.ProvinceResponseDTO;
import com.example.QuanLyPhongTro.Entity.Province;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProvinceMapper {

    Province toEntity(CreateProvinceRequestDTO dto);
    ProvinceResponseDTO toDto(Province province);
}
