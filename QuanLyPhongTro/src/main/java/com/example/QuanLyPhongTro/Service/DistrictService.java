package com.example.QuanLyPhongTro.Service;


import com.example.QuanLyPhongTro.DTO.Request.CreateDistrictRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.DistrictResponseDTO;
import com.example.QuanLyPhongTro.Entity.District;
import com.example.QuanLyPhongTro.Entity.Province;
import com.example.QuanLyPhongTro.Mapper.DistrictMapper;
import com.example.QuanLyPhongTro.Repository.DistrictRepository;
import com.example.QuanLyPhongTro.Repository.ProvinceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final ProvinceRepository provinceRepository;
    private final DistrictMapper districtMapper;

    @Transactional
    public DistrictResponseDTO createDistrict(CreateDistrictRequestDTO requestDTO) {
        District district = districtMapper.toEntity(requestDTO);
        if (districtRepository.existsByDistrictId(requestDTO.getDistrictId())){
            throw new DataException("District Id already exists", null);
        }
        else if (districtRepository.existsByDistrictName(requestDTO.getDistrictName())){
            throw new DataException("District Name already exists", null);
        }
        // Gán Province
        if (requestDTO.getProvinceId() != null) {
            Province province = provinceRepository.findById(requestDTO.getProvinceId())
                    .orElseThrow(() -> new RuntimeException("Province not found with id: " + requestDTO.getProvinceId()));
            district.setProvince(province);
        }

        district = districtRepository.save(district);
        return districtMapper.toDto(district);
    }

    public DistrictResponseDTO getDistrictById(Integer districtId) {
        return districtRepository.findById(districtId)
                .map(districtMapper::toDto)
                .orElseThrow(() -> new RuntimeException("District not found with id: " + districtId));
    }

    public List<DistrictResponseDTO> getAllDistricts() {
        return districtRepository.findAll().stream()
                .map(districtMapper::toDto)
                .toList();
    }

    @Transactional
    public void deleteDistrict(Integer districtId) {
        if (!districtRepository.existsById(districtId)) {
            throw new RuntimeException("District not found with id: " + districtId);
        }
        districtRepository.deleteById(districtId);
    }
}
