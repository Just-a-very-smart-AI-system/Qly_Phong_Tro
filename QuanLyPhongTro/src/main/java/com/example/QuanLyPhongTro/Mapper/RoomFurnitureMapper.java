package com.example.QuanLyPhongTro.Mapper;

import com.example.QuanLyPhongTro.DTO.Request.CreateRoomFurnitureRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomFurnitureResponseDTO;
import com.example.QuanLyPhongTro.Entity.RoomFurniture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoomFurnitureMapper {

    @Mapping(target = "room", ignore = true)
    RoomFurniture toEntity(CreateRoomFurnitureRequestDTO dto);

    @Mapping(target = "room", ignore = true)
    RoomFurniture toEntity(CreateRoomFurnitureRequestDTO dto, @MappingTarget RoomFurniture roomFurniture);

    default RoomFurnitureResponseDTO toDto(List<RoomFurniture> furnitureList, Integer roomId) {
        RoomFurnitureResponseDTO dto = new RoomFurnitureResponseDTO();
        dto.setRoomId(roomId);
        dto.setUtility(furnitureList.stream()
                .map(RoomFurniture::getUtilityName)
                .collect(Collectors.toList()));
        return dto;
    }
}