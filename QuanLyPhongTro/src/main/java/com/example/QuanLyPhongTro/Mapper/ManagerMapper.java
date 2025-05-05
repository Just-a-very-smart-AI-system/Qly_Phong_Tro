package com.example.QuanLyPhongTro.Mapper;

import com.example.QuanLyPhongTro.DTO.Request.CreateManagerRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.ManagerResponseDTO;
import com.example.QuanLyPhongTro.Entity.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    Manager toEntity(CreateManagerRequestDTO dto);
    ManagerResponseDTO toDto(Manager manager);
}