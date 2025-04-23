package com.example.QuanLyPhongTro.Mapper;

import com.example.QuanLyPhongTro.DTO.Request.CreateRoomMediaRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomMediaResponseDTO;
import com.example.QuanLyPhongTro.Entity.Room;
import com.example.QuanLyPhongTro.Entity.RoomMedia;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-21T14:08:56+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class RoomMediaMapperImpl implements RoomMediaMapper {

    @Override
    public RoomMedia toEntity(CreateRoomMediaRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        RoomMedia roomMedia = new RoomMedia();

        roomMedia.setUploadedAt( dto.getUploadedAt() );

        return roomMedia;
    }

    @Override
    public RoomMedia toEntity(CreateRoomMediaRequestDTO dto, RoomMedia roomMedia) {
        if ( dto == null ) {
            return roomMedia;
        }

        roomMedia.setUploadedAt( dto.getUploadedAt() );

        return roomMedia;
    }

    @Override
    public RoomMediaResponseDTO toDto(RoomMedia roomMedia) {
        if ( roomMedia == null ) {
            return null;
        }

        RoomMediaResponseDTO roomMediaResponseDTO = new RoomMediaResponseDTO();

        roomMediaResponseDTO.setRoomId( roomMediaRoomRoomId( roomMedia ) );
        roomMediaResponseDTO.setMediaId( roomMedia.getMediaId() );
        roomMediaResponseDTO.setMediaUrl( roomMedia.getMediaUrl() );
        roomMediaResponseDTO.setUploadedAt( roomMedia.getUploadedAt() );

        return roomMediaResponseDTO;
    }

    private Integer roomMediaRoomRoomId(RoomMedia roomMedia) {
        if ( roomMedia == null ) {
            return null;
        }
        Room room = roomMedia.getRoom();
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
