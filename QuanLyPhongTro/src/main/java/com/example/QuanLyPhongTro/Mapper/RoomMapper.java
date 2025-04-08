package com.example.QuanLyPhongTro.Mapper;


import com.example.QuanLyPhongTro.DTO.Request.CreateRoomRequestDTO;
import com.example.QuanLyPhongTro.DTO.Request.UpdateRoomRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomResponseDTO;
import com.example.QuanLyPhongTro.Entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoomMapper {

    @Mapping(target = "address", ignore = true)
    Room toEntity(CreateRoomRequestDTO dto);

    Room toEntity(UpdateRoomRequestDTO dto, @MappingTarget Room room);

    @Mapping(source = "manager.managerId", target = "managerId")
    @Mapping(source = "isActive", target = "isActive")
    RoomResponseDTO toDto(Room room);
}
