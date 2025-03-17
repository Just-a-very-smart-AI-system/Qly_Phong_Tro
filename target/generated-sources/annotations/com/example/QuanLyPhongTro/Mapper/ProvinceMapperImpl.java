package com.example.QuanLyPhongTro.Mapper;

import com.example.QuanLyPhongTro.DTO.Request.CreateProvinceRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.ProvinceResponseDTO;
import com.example.QuanLyPhongTro.Entity.Province;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-17T14:13:57+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class ProvinceMapperImpl implements ProvinceMapper {

    @Override
    public Province toEntity(CreateProvinceRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Province province = new Province();

        province.setProvinceName( dto.getProvinceName() );

        return province;
    }

    @Override
    public ProvinceResponseDTO toDto(Province province) {
        if ( province == null ) {
            return null;
        }

        ProvinceResponseDTO provinceResponseDTO = new ProvinceResponseDTO();

        provinceResponseDTO.setProvinceId( province.getProvinceId() );
        provinceResponseDTO.setProvinceName( province.getProvinceName() );

        return provinceResponseDTO;
    }
}
