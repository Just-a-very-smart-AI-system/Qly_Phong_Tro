package com.example.QuanLyPhongTro.Mapper;

import com.example.QuanLyPhongTro.DTO.Request.CreateUserRequestDTO;
import com.example.QuanLyPhongTro.DTO.Request.UpdateUserRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.UserResponseDTO;
import com.example.QuanLyPhongTro.Entity.Address;
import com.example.QuanLyPhongTro.Entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-05T10:29:37+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(CreateUserRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setUserName( dto.getUserName() );
        user.setPassword( dto.getPassword() );
        user.setEmail( dto.getEmail() );
        user.setPhone( dto.getPhone() );
        user.setRole( dto.getRole() );

        return user;
    }

    @Override
    public User toEntity(UpdateUserRequestDTO dto, User user) {
        if ( dto == null ) {
            return user;
        }

        user.setUserName( dto.getUserName() );
        user.setPassword( dto.getPassword() );
        user.setEmail( dto.getEmail() );
        user.setPhone( dto.getPhone() );
        user.setRole( dto.getRole() );

        return user;
    }

    @Override
    public UserResponseDTO toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setAddressId( userAddressAddressId( user ) );
        userResponseDTO.setUserId( user.getUserId() );
        userResponseDTO.setUserName( user.getUserName() );
        userResponseDTO.setEmail( user.getEmail() );
        userResponseDTO.setPhone( user.getPhone() );
        userResponseDTO.setCreatedAt( user.getCreatedAt() );
        userResponseDTO.setRole( user.getRole() );

        return userResponseDTO;
    }

    private Integer userAddressAddressId(User user) {
        if ( user == null ) {
            return null;
        }
        Address address = user.getAddress();
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
