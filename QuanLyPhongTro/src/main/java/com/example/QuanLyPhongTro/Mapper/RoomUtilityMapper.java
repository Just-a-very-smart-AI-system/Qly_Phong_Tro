package com.example.QuanLyPhongTro.Mapper;

import com.example.QuanLyPhongTro.DTO.Request.CreateRoomUtilityRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomUtilityResponseDTO;
import com.example.QuanLyPhongTro.Entity.RoomUtility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomUtilityMapper {

    @Mapping(target = "room", ignore = true)
    RoomUtility toEntity(CreateRoomUtilityRequestDTO dto);

    @Mapping(target = "room", ignore = true)
    RoomUtility toEntity(CreateRoomUtilityRequestDTO dto, @MappingTarget RoomUtility roomUtility);

    @Mapping(source = "room.roomId", target = "roomId")
    RoomUtilityResponseDTO toDto(RoomUtility roomUtility);
}
