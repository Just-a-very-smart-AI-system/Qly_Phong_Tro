package com.example.QuanLyPhongTro.Mapper;

import com.example.QuanLyPhongTro.DTO.Request.CreateDistrictRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.DistrictResponseDTO;
import com.example.QuanLyPhongTro.Entity.District;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DistrictMapper {

    @Mapping(target = "province", ignore = true)
    District toEntity(CreateDistrictRequestDTO dto);

    @Mapping(source = "province.provinceId", target = "provinceId")
    DistrictResponseDTO toDto(District district);
}
