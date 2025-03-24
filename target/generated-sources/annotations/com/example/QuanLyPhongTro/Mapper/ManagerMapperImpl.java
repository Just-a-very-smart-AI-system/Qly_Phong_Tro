package com.example.QuanLyPhongTro.Mapper;

import com.example.QuanLyPhongTro.DTO.Request.CreateManagerRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.ManagerResponseDTO;
import com.example.QuanLyPhongTro.Entity.Manager;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-24T09:50:11+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class ManagerMapperImpl implements ManagerMapper {

    @Override
    public Manager toEntity(CreateManagerRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Manager manager = new Manager();

        manager.setUserName( dto.getUserName() );
        manager.setPassword( dto.getPassword() );
        manager.setEmail( dto.getEmail() );
        manager.setPhone( dto.getPhone() );
        manager.setAddress( dto.getAddress() );
        manager.setCmnd( dto.getCmnd() );
        manager.setExtraContact( dto.getExtraContact() );
        manager.setCreatedAt( dto.getCreatedAt() );

        return manager;
    }

    @Override
    public ManagerResponseDTO toDto(Manager manager) {
        if ( manager == null ) {
            return null;
        }

        ManagerResponseDTO managerResponseDTO = new ManagerResponseDTO();

        managerResponseDTO.setManagerId( manager.getManagerId() );
        managerResponseDTO.setUserName( manager.getUserName() );
        managerResponseDTO.setEmail( manager.getEmail() );
        managerResponseDTO.setPhone( manager.getPhone() );
        managerResponseDTO.setAddress( manager.getAddress() );
        managerResponseDTO.setCmnd( manager.getCmnd() );
        managerResponseDTO.setExtraContact( manager.getExtraContact() );
        managerResponseDTO.setCreatedAt( manager.getCreatedAt() );

        return managerResponseDTO;
    }
}
