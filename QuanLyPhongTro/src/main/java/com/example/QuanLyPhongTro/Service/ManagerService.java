package com.example.QuanLyPhongTro.Service;

import com.example.QuanLyPhongTro.DTO.Request.CreateManagerRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.ManagerResponseDTO;
import com.example.QuanLyPhongTro.Entity.Manager;
import com.example.QuanLyPhongTro.Mapper.ManagerMapper;
import com.example.QuanLyPhongTro.Repository.ManagerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;

    @Transactional
    public ManagerResponseDTO createManager(CreateManagerRequestDTO requestDTO) {
        Manager manager = managerMapper.toEntity(requestDTO);
        manager.setCreatedAt(requestDTO.getCreatedAt() != null ? requestDTO.getCreatedAt() : LocalDateTime.now());
        manager = managerRepository.save(manager);
        return managerMapper.toDto(manager);
    }

    @Transactional
    public ManagerResponseDTO updateManager(Integer managerId, CreateManagerRequestDTO requestDTO) {
        Optional<Manager> optionalManager = managerRepository.findById(managerId);
        if (optionalManager.isEmpty()) {
            throw new RuntimeException("Manager not found with id: " + managerId);
        }
        Manager manager = optionalManager.get();
        managerMapper.toEntity(requestDTO);
        manager = managerRepository.save(manager);
        return managerMapper.toDto(manager);
    }

    public ManagerResponseDTO getManagerById(Integer managerId) {
        return managerRepository.findById(managerId)
                .map(managerMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Manager not found with id: " + managerId));
    }

    public List<ManagerResponseDTO> getAllManagers() {
        return managerRepository.findAll().stream()
                .map(managerMapper::toDto)
                .toList();
    }

    @Transactional
    public void deleteManager(Integer managerId) {
        if (!managerRepository.existsById(managerId)) {
            throw new RuntimeException("Manager not found with id: " + managerId);
        }
        managerRepository.deleteById(managerId);
    }
}