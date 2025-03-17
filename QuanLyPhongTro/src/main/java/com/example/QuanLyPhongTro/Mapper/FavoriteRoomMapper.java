package com.example.QuanLyPhongTro.Mapper;

import com.example.QuanLyPhongTro.DTO.Request.CreateFavoriteRoomRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.FavoriteRoomResponseDTO;
import com.example.QuanLyPhongTro.Entity.FavoriteRoom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FavoriteRoomMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "room", ignore = true)
    FavoriteRoom toEntity(CreateFavoriteRoomRequestDTO dto);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "room", ignore = true)
    FavoriteRoom toEntity(CreateFavoriteRoomRequestDTO dto, @MappingTarget FavoriteRoom favoriteRoom);

    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "room.roomId", target = "roomId")
    FavoriteRoomResponseDTO toDto(FavoriteRoom favoriteRoom);
}