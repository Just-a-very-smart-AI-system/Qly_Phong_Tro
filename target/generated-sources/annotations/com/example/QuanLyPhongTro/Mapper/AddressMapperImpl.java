package com.example.QuanLyPhongTro.Mapper;

import com.example.QuanLyPhongTro.DTO.Request.CreateAddressRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.AddressResponseDTO;
import com.example.QuanLyPhongTro.Entity.Address;
import com.example.QuanLyPhongTro.Entity.Ward;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-24T09:50:11+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address toEntity(CreateAddressRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Address address = new Address();

        address.setStreetAddress( dto.getStreetAddress() );
        address.setLatitude( dto.getLatitude() );
        address.setLongitude( dto.getLongitude() );
        address.setCreatedAt( dto.getCreatedAt() );
        address.setUpdatedAt( dto.getUpdatedAt() );

        return address;
    }

    @Override
    public Address toEntity(CreateAddressRequestDTO dto, Address address) {
        if ( dto == null ) {
            return address;
        }

        address.setStreetAddress( dto.getStreetAddress() );
        address.setLatitude( dto.getLatitude() );
        address.setLongitude( dto.getLongitude() );
        address.setCreatedAt( dto.getCreatedAt() );
        address.setUpdatedAt( dto.getUpdatedAt() );

        return address;
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
