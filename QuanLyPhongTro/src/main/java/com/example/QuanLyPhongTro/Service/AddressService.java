package com.example.QuanLyPhongTro.Service;

import com.example.QuanLyPhongTro.DTO.Request.CreateAddressRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.AddressResponseDTO;
import com.example.QuanLyPhongTro.Entity.Address;
import com.example.QuanLyPhongTro.Entity.Ward;
import com.example.QuanLyPhongTro.Mapper.AddressMapper;
import com.example.QuanLyPhongTro.Repository.AddressRepository;
import com.example.QuanLyPhongTro.Repository.WardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final WardRepository wardRepository;
    private final AddressMapper addressMapper;

    @Transactional
    public AddressResponseDTO createAddress(CreateAddressRequestDTO requestDTO) {
        Address address = addressMapper.toEntity(requestDTO);
        address.setCreatedAt(LocalDateTime.now());
        address.setUpdatedAt(LocalDateTime.now());

        // Gán Ward
        if (requestDTO.getWardId() != null) {
            Ward ward = wardRepository.findById(requestDTO.getWardId())
                    .orElseThrow(() -> new RuntimeException("Ward not found with id: " + requestDTO.getWardId()));
            address.setWard(ward);
        }

        address = addressRepository.save(address);
        return addressMapper.toDto(address);
    }

    @Transactional
    public AddressResponseDTO updateAddress(Integer addressId, CreateAddressRequestDTO requestDTO) {
        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        if (optionalAddress.isEmpty()) {
            throw new RuntimeException("Address not found with id: " + addressId);
        }
        Address address = optionalAddress.get();
        addressMapper.toEntity(requestDTO, address);
        address.setUpdatedAt(LocalDateTime.now());

        // Cập nhật Ward nếu có
        if (requestDTO.getWardId() != null) {
            Ward ward = wardRepository.findById(requestDTO.getWardId())
                    .orElseThrow(() -> new RuntimeException("Ward not found with id: " + requestDTO.getWardId()));
            address.setWard(ward);
        }

        address = addressRepository.save(address);
        return addressMapper.toDto(address);
    }

    public AddressResponseDTO getAddressById(Integer addressId) {
        return addressRepository.findById(addressId)
                .map(addressMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + addressId));
    }

    public List<AddressResponseDTO> getAllAddresses() {
        return addressRepository.findAll().stream()
                .map(addressMapper::toDto)
                .toList();
    }

    @Transactional
    public void deleteAddress(Integer addressId) {
        if (!addressRepository.existsById(addressId)) {
            throw new RuntimeException("Address not found with id: " + addressId);
        }
        addressRepository.deleteById(addressId);
    }
}