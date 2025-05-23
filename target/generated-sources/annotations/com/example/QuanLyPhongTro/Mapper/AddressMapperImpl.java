package com.example.QuanLyPhongTro.Mapper;

import com.example.QuanLyPhongTro.DTO.Request.CreateAddressRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.AddressResponseDTO;
import com.example.QuanLyPhongTro.Entity.Address;
import com.example.QuanLyPhongTro.Entity.Ward;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-06T07:34:21+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address toEntity(CreateAddressRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        Address address = new Address();

        address.setStreetAddress( requestDTO.getStreetAddress() );

        return address;
    }

    @Override
    public void toEntity(CreateAddressRequestDTO requestDTO, Address address) {
        if ( requestDTO == null ) {
            return;
        }

        address.setStreetAddress( requestDTO.getStreetAddress() );
    }

    @Override
    public AddressResponseDTO toDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressResponseDTO addressResponseDTO = new AddressResponseDTO();

        addressResponseDTO.setWardId( addressWardWardId( address ) );
        addressResponseDTO.setAddressId( address.getAddressId() );
        addressResponseDTO.setStreetAddress( address.getStreetAddress() );
        addressResponseDTO.setLatitude( address.getLatitude() );
        addressResponseDTO.setLongitude( address.getLongitude() );
        addressResponseDTO.setCreatedAt( address.getCreatedAt() );
        addressResponseDTO.setUpdatedAt( address.getUpdatedAt() );

        return addressResponseDTO;
    }

    private Integer addressWardWardId(Address address) {
        if ( address == null ) {
            return null;
        }
        Ward ward = address.getWard();
        if ( ward == null ) {
            return null;
        }
        Integer wardId = ward.getWardId();
        if ( wardId == null ) {
            return null;
        }
        return wardId;
    }
}
