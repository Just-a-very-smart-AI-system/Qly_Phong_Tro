package com.example.QuanLyPhongTro.Mapper;


import com.example.QuanLyPhongTro.DTO.Request.CreateRoomRequestDTO;
import com.example.QuanLyPhongTro.DTO.Request.UpdateRoomRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomResponseDTO;
import com.example.QuanLyPhongTro.Entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(target = "manager", ignore = true)
    @Mapping(target = "address", ignore = true)
    Room toEntity(CreateRoomRequestDTO dto);

    @Mapping(target = "manager", ignore = true)
    @Mapping(target = "address", ignore = true)
    Room toEntity(UpdateRoomRequestDTO dto, @MappingTarget Room room);

    @Mapping(source = "manager.managerId", target = "managerId")
    @Mapping(source = "address.addressId", target = "addressId")
    RoomResponseDTO toDto(Room room);
}
