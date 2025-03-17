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
    Address toEntity(CreateAddressRequestDTO dto);

    @Mapping(target = "ward", ignore = true)
    Address toEntity(CreateAddressRequestDTO dto, @MappingTarget Address address);

    @Mapping(source = "ward.wardId", target = "wardId")
    AddressResponseDTO toDto(Address address);
}
