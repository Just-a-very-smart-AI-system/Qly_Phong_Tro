package com.example.QuanLyPhongTro.Mapper;

import com.example.QuanLyPhongTro.DTO.Request.CreateFavoriteRoomRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.FavoriteRoomResponseDTO;
import com.example.QuanLyPhongTro.Entity.FavoriteRoom;
import com.example.QuanLyPhongTro.Entity.Room;
import com.example.QuanLyPhongTro.Entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-17T14:13:57+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class FavoriteRoomMapperImpl implements FavoriteRoomMapper {

    @Override
    public FavoriteRoom toEntity(CreateFavoriteRoomRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FavoriteRoom favoriteRoom = new FavoriteRoom();

        favoriteRoom.setAddedAt( dto.getAddedAt() );

        return favoriteRoom;
    }

    @Override
    public FavoriteRoom toEntity(CreateFavoriteRoomRequestDTO dto, FavoriteRoom favoriteRoom) {
        if ( dto == null ) {
            return favoriteRoom;
        }

        favoriteRoom.setAddedAt( dto.getAddedAt() );

        return favoriteRoom;
    }

    @Override
    public FavoriteRoomResponseDTO toDto(FavoriteRoom favoriteRoom) {
        if ( favoriteRoom == null ) {
            return null;
        }

        FavoriteRoomResponseDTO favoriteRoomResponseDTO = new FavoriteRoomResponseDTO();

        favoriteRoomResponseDTO.setUserId( favoriteRoomUserUserId( favoriteRoom ) );
        favoriteRoomResponseDTO.setRoomId( favoriteRoomRoomRoomId( favoriteRoom ) );
        favoriteRoomResponseDTO.setFavoriteId( favoriteRoom.getFavoriteId() );
        favoriteRoomResponseDTO.setAddedAt( favoriteRoom.getAddedAt() );

        return favoriteRoomResponseDTO;
    }

    private Integer favoriteRoomUserUserId(FavoriteRoom favoriteRoom) {
        if ( favoriteRoom == null ) {
            return null;
        }
        User user = favoriteRoom.getUser();
        if ( user == null ) {
            return null;
        }
        Integer userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private Integer favoriteRoomRoomRoomId(FavoriteRoom favoriteRoom) {
        if ( favoriteRoom == null ) {
            return null;
        }
        Room room = favoriteRoom.getRoom();
        if ( room == null ) {
            return null;
        }
        Integer roomId = room.getRoomId();
        if ( roomId == null ) {
            return null;
        }
        return roomId;
    }
}
