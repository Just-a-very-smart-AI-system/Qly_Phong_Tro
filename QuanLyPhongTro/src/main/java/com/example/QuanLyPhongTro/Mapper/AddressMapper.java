package com.example.QuanLyPhongTro.Mapper;

import com.example.QuanLyPhongTro.DTO.Request.CreateAddressRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.AddressResponseDTO;
import com.example.QuanLyPhongTro.Entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "ward", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Address toEntity(CreateAddressRequestDTO requestDTO);

    @Mapping(target = "ward", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void toEntity(CreateAddressRequestDTO requestDTO, @MappingTarget Address address);

    @Mapping(source = "ward.wardId", target = "wardId")
    AddressResponseDTO toDto(Address address);
}