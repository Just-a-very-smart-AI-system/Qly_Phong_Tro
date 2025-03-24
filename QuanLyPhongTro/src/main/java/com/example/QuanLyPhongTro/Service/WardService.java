package com.example.QuanLyPhongTro.Service;

import com.example.QuanLyPhongTro.DTO.Request.CreateWardRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.WardResponseDTO;
import com.example.QuanLyPhongTro.Entity.District;
import com.example.QuanLyPhongTro.Entity.Ward;
import com.example.QuanLyPhongTro.Mapper.WardMapper;
import com.example.QuanLyPhongTro.Repository.DistrictRepository;
import com.example.QuanLyPhongTro.Repository.WardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WardService {

    private final WardRepository wardRepository;
    private final DistrictRepository districtRepository;
    private final WardMapper wardMapper;

    @Transactional
    public WardResponseDTO createWard(CreateWardRequestDTO requestDTO) {
        Ward ward = wardMapper.toEntity(requestDTO);
        if(wardRepository.existsByWardId(requestDTO.getWardId())){
            throw new DataException("Ward Id already exists", null);
        }
        // Gán District
        if (requestDTO.getDistrictId() != null) {
            District district = districtRepository.findById(requestDTO.getDistrictId())
                    .orElseThrow(() -> new RuntimeException("District not found with id: " + requestDTO.getDistrictId()));
            ward.setDistrict(district);
        }

        ward = wardRepository.save(ward);
        return wardMapper.toDto(ward);
    }

    public WardResponseDTO getWardById(Integer wardId) {
        return wardRepository.findById(wardId)
                .map(wardMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Ward not found with id: " + wardId));
    }

    public List<WardResponseDTO> getAllWards() {
        return wardRepository.findAll().stream()
                .map(wardMapper::toDto)
                .toList();
    }

    @Transactional
    public void deleteWard(Integer wardId) {
        if (!wardRepository.existsById(wardId)) {
            throw new RuntimeException("Ward not found with id: " + wardId);
        }
        wardRepository.deleteById(wardId);
    }
}
