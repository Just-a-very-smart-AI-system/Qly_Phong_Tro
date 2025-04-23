package com.example.QuanLyPhongTro.Mapper;

import com.example.QuanLyPhongTro.DTO.Request.CreateRoomRequestDTO;
import com.example.QuanLyPhongTro.DTO.Request.UpdateRoomRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomResponseDTO;
import com.example.QuanLyPhongTro.Entity.Manager;
import com.example.QuanLyPhongTro.Entity.Room;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-21T14:08:56+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public Room toEntity(CreateRoomRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Room room = new Room();

        room.setPrice( dto.getPrice() );
        room.setArea( dto.getArea() );
        room.setMaxOccupants( dto.getMaxOccupants() );
        room.setDescription( dto.getDescription() );
        room.setTitle( dto.getTitle() );

        return room;
    }

    @Override
    public Room toEntity(UpdateRoomRequestDTO dto, Room room) {
        if ( dto == null ) {
            return room;
        }

        if ( dto.getPrice() != null ) {
            room.setPrice( dto.getPrice() );
        }
        if ( dto.getArea() != null ) {
            room.setArea( dto.getArea() );
        }
        if ( dto.getMaxOccupants() != null ) {
            room.setMaxOccupants( dto.getMaxOccupants() );
        }
        if ( dto.getDescription() != null ) {
            room.setDescription( dto.getDescription() );
        }
        if ( dto.getIsActive() != null ) {
            room.setIsActive( dto.getIsActive() );
        }

        return room;
    }

    @Override
    public RoomResponseDTO toDto(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomResponseDTO roomResponseDTO = new RoomResponseDTO();

        roomResponseDTO.setManagerId( roomManagerManagerId( room ) );
        roomResponseDTO.setIsActive( room.getIsActive() );
        roomResponseDTO.setRoomId( room.getRoomId() );
        roomResponseDTO.setTitle( room.getTitle() );
        roomResponseDTO.setDescription( room.getDescription() );
        roomResponseDTO.setPrice( room.getPrice() );
        roomResponseDTO.setArea( room.getArea() );
        roomResponseDTO.setMaxOccupants( room.getMaxOccupants() );
        roomResponseDTO.setCreatedAt( room.getCreatedAt() );
        roomResponseDTO.setUpdatedAt( room.getUpdatedAt() );

        return roomResponseDTO;
    }

    private Integer roomManagerManagerId(Room room) {
        if ( room == null ) {
            return null;
        }
        Manager manager = room.getManager();
        if ( manager == null ) {
            return null;
        }
        Integer managerId = manager.getManagerId();
        if ( managerId == null ) {
            return null;
        }
        return managerId;
    }
}
