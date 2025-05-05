package com.example.QuanLyPhongTro.Mapper;

import com.example.QuanLyPhongTro.DTO.Request.CreateWardRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.WardResponseDTO;
import com.example.QuanLyPhongTro.Entity.District;
import com.example.QuanLyPhongTro.Entity.Ward;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-05T10:29:37+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class WardMapperImpl implements WardMapper {

    @Override
    public Ward toEntity(CreateWardRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Ward ward = new Ward();

        ward.setWardId( dto.getWardId() );
        ward.setWardName( dto.getWardName() );

        return ward;
    }

    @Override
    public WardResponseDTO toDto(Ward ward) {
        if ( ward == null ) {
            return null;
        }

        WardResponseDTO wardResponseDTO = new WardResponseDTO();

        wardResponseDTO.setWardId( ward.getWardId() );
        wardResponseDTO.setDistrictId( wardDistrictDistrictId( ward ) );
        wardResponseDTO.setWardName( ward.getWardName() );

        return wardResponseDTO;
    }

    private Integer wardDistrictDistrictId(Ward ward) {
        if ( ward == null ) {
            return null;
        }
        District district = ward.getDistrict();
        if ( district == null ) {
            return null;
        }
        Integer districtId = district.getDistrictId();
        if ( districtId == null ) {
            return null;
        }
        return districtId;
    }
}
