package com.example.QuanLyPhongTro.Mapper;


import com.example.QuanLyPhongTro.DTO.Request.CreateWardRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.WardResponseDTO;
import com.example.QuanLyPhongTro.Entity.Ward;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WardMapper {
    @Mapping(source = "wardId", target = "wardId")
    @Mapping(target = "district", ignore = true)
    Ward toEntity(CreateWardRequestDTO dto);
    @Mapping(source = "wardId", target = "wardId")
    @Mapping(source = "district.districtId", target = "districtId")
    WardResponseDTO toDto(Ward ward);
}
