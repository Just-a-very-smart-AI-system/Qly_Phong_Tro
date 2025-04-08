package com.example.QuanLyPhongTro.Service;

import com.example.QuanLyPhongTro.DTO.Request.CreateAddressRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.*;
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
    private final WardService wardService;
    private final DistrictService districtService;
    private final ProvinceService provinceService;
    private final LocationService  locationService;

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

        AddressResponseDTO addressResponseDTO =  toDtoWithWardAddress(address);
        String fullAddress = String.format("%s, %s",requestDTO.getStreetAddress(), addressResponseDTO.getWardAddress());

        CoordinatesDTO coordinatesDTO = locationService.getCoordinatesFromAddress(fullAddress);

        address.setLongitude(coordinatesDTO.getLongitude());
        address.setLatitude(coordinatesDTO.getLatitude());

        addressRepository.save(address);

        addressResponseDTO = addressMapper.toDto(address);
        addressResponseDTO.setWardAddress(fullAddress);
        addressResponseDTO.setAddressId(address.getAddressId());

        return addressResponseDTO;
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
        return toDtoWithWardAddress(address);
    }

    public AddressResponseDTO getAddressById(Integer addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + addressId));
        return toDtoWithWardAddress(address);
    }

    public List<AddressResponseDTO> getAllAddresses() {
        return addressRepository.findAll().stream()
                .map(this::toDtoWithWardAddress)
                .toList();
    }

    @Transactional
    public void deleteAddress(Integer addressId) {
        if (!addressRepository.existsById(addressId)) {
            throw new RuntimeException("Address not found with id: " + addressId);
        }
        addressRepository.deleteById(addressId);
    }

    // Phương thức chuyển đổi Address thành AddressResponseDTO và gán wardAddress
    private AddressResponseDTO toDtoWithWardAddress(Address address) {
        AddressResponseDTO dto = addressMapper.toDto(address);

        // Lấy thông tin wardName, districtName, provinceName và ghép vào wardAddress
        if (address.getWard() != null) {
            // Lấy wardName từ Ward
            WardResponseDTO wardDTO = wardService.getWardById(address.getWard().getWardId());
            String wardName = wardDTO.getWardName();

            // Lấy districtName từ District
            DistrictResponseDTO districtDTO = districtService.getDistrictById(wardDTO.getDistrictId());
            String districtName = districtDTO.getDistrictName();

            // Lấy provinceName từ Province
            ProvinceResponseDTO provinceDTO = provinceService.getProvinceById(districtDTO.getProvinceId());
            String provinceName = provinceDTO.getProvinceName();

            // Ghép chuỗi: "wardName, districtName, provinceName"
            String wardAddress = String.format("%s, %s, %s", wardName, districtName, provinceName);

            dto.setWardAddress(wardAddress);
        }
        return dto;
    }
    // Tìm kiếm Address theo provinceName
    public List<AddressResponseDTO> findByProvinceName(String provinceName) {
        if (provinceName == null || provinceName.trim().isEmpty()) {
            return getAllAddresses(); // Nếu không có provinceName, trả về tất cả
        }
        List<Address> addresses = addressRepository.findByProvinceName(provinceName);
        return addresses.stream()
                .map(this::toDtoWithWardAddress)
                .toList();
    }

    // Tìm kiếm Address theo districtName
    public List<AddressResponseDTO> findByDistrictName(String districtName) {
        if (districtName == null || districtName.trim().isEmpty()) {
            return getAllAddresses(); // Nếu không có districtName, trả về tất cả
        }
        List<Address> addresses = addressRepository.findByDistrictName(districtName);
        return addresses.stream()
                .map(this::toDtoWithWardAddress)
                .toList();
    }

    // Tìm kiếm Address theo districtId
    public List<AddressResponseDTO> findByDistrictId(Integer districtId) {
        if (districtId == null) {
            return getAllAddresses(); // Nếu không có districtId, trả về tất cả
        }
        List<Address> addresses = addressRepository.findByDistrictId(districtId);
        return addresses.stream()
                .map(this::toDtoWithWardAddress)
                .toList();
    }

    // Tìm kiếm Address theo provinceId
    public List<AddressResponseDTO> findByProvinceId(Integer provinceId) {
        if (provinceId == null) {
            return getAllAddresses(); // Nếu không có provinceId, trả về tất cả
        }
        List<Address> addresses = addressRepository.findByProvinceId(provinceId);
        return addresses.stream()
                .map(this::toDtoWithWardAddress)
                .toList();
    }
}