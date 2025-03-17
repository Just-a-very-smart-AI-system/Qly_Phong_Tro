package com.example.QuanLyPhongTro.Service;


import com.example.QuanLyPhongTro.DTO.Request.CreateProvinceRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.ProvinceResponseDTO;
import com.example.QuanLyPhongTro.Entity.Province;
import com.example.QuanLyPhongTro.Mapper.ProvinceMapper;
import com.example.QuanLyPhongTro.Repository.ProvinceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinceService {

    private final ProvinceRepository provinceRepository;
    private final ProvinceMapper provinceMapper;

    @Transactional
    public ProvinceResponseDTO createProvince(CreateProvinceRequestDTO requestDTO) {
        Province province = provinceMapper.toEntity(requestDTO);
        province = provinceRepository.save(province);
        return provinceMapper.toDto(province);
    }

    public ProvinceResponseDTO getProvinceById(Integer provinceId) {
        return provinceRepository.findById(provinceId)
                .map(provinceMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Province not found with id: " + provinceId));
    }

    public List<ProvinceResponseDTO> getAllProvinces() {
        return provinceRepository.findAll().stream()
                .map(provinceMapper::toDto)
                .toList();
    }

    @Transactional
    public void deleteProvince(Integer provinceId) {
        if (!provinceRepository.existsById(provinceId)) {
            throw new RuntimeException("Province not found with id: " + provinceId);
        }
        provinceRepository.deleteById(provinceId);
    }
}
