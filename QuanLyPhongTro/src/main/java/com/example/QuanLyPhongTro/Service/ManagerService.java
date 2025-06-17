package com.example.QuanLyPhongTro.Service;

import com.example.QuanLyPhongTro.DTO.Request.CreateManagerRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.ManagerResponseDTO;
import com.example.QuanLyPhongTro.Entity.Manager;
import com.example.QuanLyPhongTro.Mapper.ManagerMapper;
import com.example.QuanLyPhongTro.Repository.ManagerRepository;
import com.example.QuanLyPhongTro.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerService {
    @Autowired
    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;
    private final ManagerMapper managerMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ManagerResponseDTO createManager(CreateManagerRequestDTO requestDTO) {
        if (managerRepository.existsByUserName(requestDTO.getUserName())) {
            throw new DataException("Username already exists", null);
        }
        if (managerRepository.existsByEmail(requestDTO.getEmail())) {
            throw new DataException("Email already exists", null);
        }
        if (userRepository.existsByUserName(requestDTO.getUserName())) {
            throw new DataException("Username already exists", null);
        }
        if (userRepository.existsByEmail(requestDTO.getEmail())) {
            throw new DataException("Email already exists", null);
        }
        Manager manager = managerMapper.toEntity(requestDTO);
        manager.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
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
        manager.setPassword(passwordEncoder.encode(requestDTO.getPassword()));

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
    public Manager findByUserName(String name){
        return managerRepository.findByUserName(name)
                .orElseThrow(() -> new RuntimeException("UserName not found"));
    }
    @Transactional
    public void deleteManager(Integer managerId) {
        if (!managerRepository.existsById(managerId)) {
            throw new RuntimeException("Manager not found with id: " + managerId);
        }
        managerRepository.deleteById(managerId);
    }
}