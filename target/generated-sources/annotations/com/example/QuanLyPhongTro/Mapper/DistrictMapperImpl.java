package com.example.QuanLyPhongTro.Mapper;

import com.example.QuanLyPhongTro.DTO.Request.CreateDistrictRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.DistrictResponseDTO;
import com.example.QuanLyPhongTro.Entity.District;
import com.example.QuanLyPhongTro.Entity.Province;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-05T10:29:37+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class DistrictMapperImpl implements DistrictMapper {

    @Override
    public District toEntity(CreateDistrictRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        District district = new District();

        district.setDistrictId( dto.getDistrictId() );
        district.setDistrictName( dto.getDistrictName() );

        return district;
    }

    @Override
    public DistrictResponseDTO toDto(District district) {
        if ( district == null ) {
            return null;
        }

        DistrictResponseDTO districtResponseDTO = new DistrictResponseDTO();

        districtResponseDTO.setProvinceId( districtProvinceProvinceId( district ) );
        districtResponseDTO.setDistrictId( district.getDistrictId() );
        districtResponseDTO.setDistrictName( district.getDistrictName() );

        return districtResponseDTO;
    }

    private Integer districtProvinceProvinceId(District district) {
        if ( district == null ) {
            return null;
        }
        Province province = district.getProvince();
        if ( province == null ) {
            return null;
        }
        Integer provinceId = province.getProvinceId();
        if ( provinceId == null ) {
            return null;
        }
        return provinceId;
    }
}
