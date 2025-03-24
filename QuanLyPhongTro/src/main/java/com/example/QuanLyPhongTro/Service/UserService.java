package com.example.QuanLyPhongTro.Service;


import com.example.QuanLyPhongTro.DTO.Request.CreateUserRequestDTO;
import com.example.QuanLyPhongTro.DTO.Request.UpdateUserRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.UserResponseDTO;
import com.example.QuanLyPhongTro.Entity.Address;
import com.example.QuanLyPhongTro.Entity.User;
import com.example.QuanLyPhongTro.Mapper.UserMapper;
import com.example.QuanLyPhongTro.Repository.AddressRepository;
import com.example.QuanLyPhongTro.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.DataException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserResponseDTO createUser(CreateUserRequestDTO requestDTO) {
        User user = userMapper.toEntity(requestDTO);
        user.setCreatedAt(LocalDateTime.now());
        if (userRepository.existsByUserName(requestDTO.getUserName())) {
            throw new DataException("Username already exists", null);
        }
        if (userRepository.existsByEmail(requestDTO.getEmail())) {
            throw new DataException("Email already exists", null);
        }
        // Gán Address nếu addressId được cung cấp
        if (requestDTO.getAddressId() != null) {
            Address address = addressRepository.findById(requestDTO.getAddressId())
                    .orElseThrow(() -> new RuntimeException("Address not found with id: " + requestDTO.getAddressId()));
            user.setAddress(address);
        }

        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Transactional
    public UserResponseDTO updateUser(Integer userId, UpdateUserRequestDTO requestDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        User user = optionalUser.get();
        userMapper.toEntity(requestDTO, user);

        // Cập nhật Address nếu addressId được cung cấp
        if (requestDTO.getAddressId() != null) {
            Address address = addressRepository.findById(requestDTO.getAddressId())
                    .orElseThrow(() -> new RuntimeException("Address not found with id: " + requestDTO.getAddressId()));
            user.setAddress(address);
        }

        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserResponseDTO getUserById(Integer userId) {
        return userRepository.findById(userId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Transactional
    public void deleteUser(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }
}