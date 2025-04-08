package com.example.QuanLyPhongTro.Mapper;

import com.example.QuanLyPhongTro.DTO.Request.CreateRoomUtilityRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomUtilityResponseDTO;
import com.example.QuanLyPhongTro.Entity.Room;
import com.example.QuanLyPhongTro.Entity.RoomUtility;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-08T14:01:58+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class RoomUtilityMapperImpl implements RoomUtilityMapper {

    @Override
    public RoomUtility toEntity(CreateRoomUtilityRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        RoomUtility roomUtility = new RoomUtility();

        roomUtility.setUtilityName( dto.getUtilityName() );

        return roomUtility;
    }

    @Override
    public RoomUtility toEntity(CreateRoomUtilityRequestDTO dto, RoomUtility roomUtility) {
        if ( dto == null ) {
            return roomUtility;
        }

        roomUtility.setUtilityName( dto.getUtilityName() );

        return roomUtility;
    }

    @Override
    public RoomUtilityResponseDTO toDto(RoomUtility roomUtility) {
        if ( roomUtility == null ) {
            return null;
        }

        RoomUtilityResponseDTO roomUtilityResponseDTO = new RoomUtilityResponseDTO();

        roomUtilityResponseDTO.setRoomId( roomUtilityRoomRoomId( roomUtility ) );
        roomUtilityResponseDTO.setUtilityId( roomUtility.getUtilityId() );
        roomUtilityResponseDTO.setUtilityName( roomUtility.getUtilityName() );

        return roomUtilityResponseDTO;
    }

    private Integer roomUtilityRoomRoomId(RoomUtility roomUtility) {
        if ( roomUtility == null ) {
            return null;
        }
        Room room = roomUtility.getRoom();
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
