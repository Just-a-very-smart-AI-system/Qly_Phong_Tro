package com.example.QuanLyPhongTro.Mapper;


import com.example.QuanLyPhongTro.Entity.User;
import com.example.QuanLyPhongTro.DTO.Request.CreateUserRequestDTO;
import com.example.QuanLyPhongTro.DTO.Request.UpdateUserRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "address", ignore = true)
    User toEntity(CreateUserRequestDTO dto);

    @Mapping(target = "address", ignore = true)
    User toEntity(UpdateUserRequestDTO dto, @MappingTarget User user);

    @Mapping(source = "address.addressId", target = "addressId")
    UserResponseDTO toDto(User user);
}