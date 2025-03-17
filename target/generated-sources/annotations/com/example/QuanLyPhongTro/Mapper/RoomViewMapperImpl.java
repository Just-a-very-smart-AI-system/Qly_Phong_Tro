package com.example.QuanLyPhongTro.Mapper;

import com.example.QuanLyPhongTro.DTO.Request.CreateRoomViewRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomViewResponseDTO;
import com.example.QuanLyPhongTro.Entity.Room;
import com.example.QuanLyPhongTro.Entity.RoomView;
import com.example.QuanLyPhongTro.Entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-17T14:13:57+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class RoomViewMapperImpl implements RoomViewMapper {

    @Override
    public RoomView toEntity(CreateRoomViewRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        RoomView roomView = new RoomView();

        roomView.setViewedAt( dto.getViewedAt() );

        return roomView;
    }

    @Override
    public RoomView toEntity(CreateRoomViewRequestDTO dto, RoomView roomView) {
        if ( dto == null ) {
            return roomView;
        }

        roomView.setViewedAt( dto.getViewedAt() );

        return roomView;
    }

    @Override
    public RoomViewResponseDTO toDto(RoomView roomView) {
        if ( roomView == null ) {
            return null;
        }

        RoomViewResponseDTO roomViewResponseDTO = new RoomViewResponseDTO();

        roomViewResponseDTO.setUserId( roomViewUserUserId( roomView ) );
        roomViewResponseDTO.setRoomId( roomViewRoomRoomId( roomView ) );
        roomViewResponseDTO.setViewId( roomView.getViewId() );
        roomViewResponseDTO.setViewedAt( roomView.getViewedAt() );

        return roomViewResponseDTO;
    }

    private Integer roomViewUserUserId(RoomView roomView) {
        if ( roomView == null ) {
            return null;
        }
        User user = roomView.getUser();
        if ( user == null ) {
            return null;
        }
        Integer userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private Integer roomViewRoomRoomId(RoomView roomView) {
        if ( roomView == null ) {
            return null;
        }
        Room room = roomView.getRoom();
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
