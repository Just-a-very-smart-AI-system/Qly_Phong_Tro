package com.example.QuanLyPhongTro.Mapper;

import com.example.QuanLyPhongTro.DTO.Request.CreateRoomMediaRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomMediaResponseDTO;
import com.example.QuanLyPhongTro.Entity.RoomMedia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomMediaMapper {

    @Mapping(target = "room", ignore = true)
    RoomMedia toEntity(CreateRoomMediaRequestDTO dto);

    @Mapping(target = "room", ignore = true)
    RoomMedia toEntity(CreateRoomMediaRequestDTO dto, @MappingTarget RoomMedia roomMedia);

    @Mapping(source = "room.roomId", target = "roomId")
    RoomMediaResponseDTO toDto(RoomMedia roomMedia);
}