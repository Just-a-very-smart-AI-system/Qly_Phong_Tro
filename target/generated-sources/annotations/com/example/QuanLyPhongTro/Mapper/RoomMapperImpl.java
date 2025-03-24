package com.example.QuanLyPhongTro.Mapper;

import com.example.QuanLyPhongTro.DTO.Request.CreateRoomRequestDTO;
import com.example.QuanLyPhongTro.DTO.Request.UpdateRoomRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomResponseDTO;
import com.example.QuanLyPhongTro.Entity.Address;
import com.example.QuanLyPhongTro.Entity.Manager;
import com.example.QuanLyPhongTro.Entity.Room;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-24T09:50:11+0700",
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

        room.setTitle( dto.getTitle() );
        room.setDescription( dto.getDescription() );
        room.setPrice( dto.getPrice() );
        room.setArea( dto.getArea() );
        room.setMaxOccupants( dto.getMaxOccupants() );
        room.setCreatedAt( dto.getCreatedAt() );
        room.setUpdatedAt( dto.getUpdatedAt() );

        return room;
    }

    @Override
    public Room toEntity(UpdateRoomRequestDTO dto, Room room) {
        if ( dto == null ) {
            return room;
        }

        room.setTitle( dto.getTitle() );
        room.setDescription( dto.getDescription() );
        room.setPrice( dto.getPrice() );
        room.setArea( dto.getArea() );
        room.setMaxOccupants( dto.getMaxOccupants() );
        room.setUpdatedAt( dto.getUpdatedAt() );

        return room;
    }

    @Override
    public RoomResponseDTO toDto(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomResponseDTO roomResponseDTO = new RoomResponseDTO();

        roomResponseDTO.setManagerId( roomManagerManagerId( room ) );
        roomResponseDTO.setAddressId( roomAddressAddressId( room ) );
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

    private Integer roomAddressAddressId(Room room) {
        if ( room == null ) {
            return null;
        }
        Address address = room.getAddress();
        if ( address == null ) {
            return null;
        }
        Integer addressId = address.getAddressId();
        if ( addressId == null ) {
            return null;
        }
        return addressId;
    }
}
