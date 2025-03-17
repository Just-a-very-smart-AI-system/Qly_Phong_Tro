package com.example.QuanLyPhongTro.Mapper;

import com.example.QuanLyPhongTro.DTO.Request.CreateRoomViewRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomViewResponseDTO;
import com.example.QuanLyPhongTro.Entity.RoomView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomViewMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "room", ignore = true)
    RoomView toEntity(CreateRoomViewRequestDTO dto);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "room", ignore = true)
    RoomView toEntity(CreateRoomViewRequestDTO dto, @MappingTarget RoomView roomView);

    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "room.roomId", target = "roomId")
    RoomViewResponseDTO toDto(RoomView roomView);
}