package com.example.QuanLyPhongTro.Mapper;


import com.example.QuanLyPhongTro.DTO.Request.CreateProvinceRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.ProvinceResponseDTO;
import com.example.QuanLyPhongTro.Entity.Province;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProvinceMapper {
    @Mapping(source = "provinceId", target = "provinceId")
    Province toEntity(CreateProvinceRequestDTO dto);
    @Mapping(source = "provinceId", target = "provinceId")
    ProvinceResponseDTO toDto(Province province);
}
