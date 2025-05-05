package com.example.QuanLyPhongTro.Mapper;

import com.example.QuanLyPhongTro.DTO.Request.CreateRoomFurnitureRequestDTO;
import com.example.QuanLyPhongTro.Entity.RoomFurniture;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-05T10:29:37+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class RoomFurnitureMapperImpl implements RoomFurnitureMapper {

    @Override
    public RoomFurniture toEntity(CreateRoomFurnitureRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        RoomFurniture roomFurniture = new RoomFurniture();

        roomFurniture.setUtilityName( dto.getUtilityName() );

        return roomFurniture;
    }

    @Override
    public RoomFurniture toEntity(CreateRoomFurnitureRequestDTO dto, RoomFurniture roomFurniture) {
        if ( dto == null ) {
            return roomFurniture;
        }

        roomFurniture.setUtilityName( dto.getUtilityName() );

        return roomFurniture;
    }
}
