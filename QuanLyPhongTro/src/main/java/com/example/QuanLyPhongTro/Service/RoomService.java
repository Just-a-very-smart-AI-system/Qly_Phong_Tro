package com.example.QuanLyPhongTro.Service;

import com.example.QuanLyPhongTro.DTO.Request.CreateAddressRequestDTO;
import com.example.QuanLyPhongTro.DTO.Request.CreateRoomRequestDTO;
import com.example.QuanLyPhongTro.DTO.Request.UpdateRoomRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.AddressResponseDTO;
import com.example.QuanLyPhongTro.DTO.Response.CoordinatesDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomResponseDTO;
import com.example.QuanLyPhongTro.Entity.Address;
import com.example.QuanLyPhongTro.Entity.Manager;
import com.example.QuanLyPhongTro.Entity.Room;
import com.example.QuanLyPhongTro.Mapper.RoomMapper;
import com.example.QuanLyPhongTro.Repository.AddressRepository;
import com.example.QuanLyPhongTro.Repository.ManagerRepository;
import com.example.QuanLyPhongTro.Repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final AddressRepository addressRepository;
    private final RoomMapper roomMapper;
    private final AddressService addressService;
    private final ManagerRepository managerRepository;

    @Transactional
    public RoomResponseDTO createRoom(CreateRoomRequestDTO requestDTO) {
        Room room = roomMapper.toEntity(requestDTO);
        CreateAddressRequestDTO createAddressRequestDTO = new CreateAddressRequestDTO(requestDTO.getStreetAddress(), requestDTO.getWardId());
        // Gán Address
        AddressResponseDTO addressResponseDTO = addressService.createAddress(createAddressRequestDTO);
        Address address = addressRepository.findById(addressResponseDTO.getAddressId())
                .orElseThrow(() -> new RuntimeException( "Address not found with id: " + addressResponseDTO.getAddressId()));
        room.setAddress(address);
        Manager manager = managerRepository.findById(requestDTO.getManagerId())
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + requestDTO.getManagerId()));
        room.setManager(manager);

        room.setIsActive(1);
        room.setCreatedAt(LocalDateTime.now());
        room.setUpdatedAt(LocalDateTime.now());

        // Lưu room vào cơ sở dữ liệu
        room = roomRepository.save(room);

        // Chuyển đổi thành DTO và gán thông tin địa chỉ
        RoomResponseDTO roomResponseDTO = roomMapper.toDto(room);
        roomResponseDTO.setAddressId(addressResponseDTO.getAddressId());

        return roomResponseDTO;
    }

    @Transactional
    public RoomResponseDTO updateRoom(Integer roomId, UpdateRoomRequestDTO requestDTO) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isEmpty()) {
            throw new RuntimeException("Room not found with id: " + roomId);
        }
        Room room = optionalRoom.get();
        roomMapper.toEntity(requestDTO, room);
        room.setUpdatedAt(LocalDateTime.now());

        // Lưu room vào cơ sở dữ liệu
        room = roomRepository.save(room);

        // Chuyển đổi thành DTO và gán thông tin địa chỉ
        RoomResponseDTO roomResponseDTO = roomMapper.toDto(room);
        AddressResponseDTO addressResponseDTO = addressService.getAddressById(room.getAddress().getAddressId());
        roomResponseDTO.setAddressId(addressResponseDTO.getAddressId());

        return roomResponseDTO;
    }

    public RoomResponseDTO getRoomById(Integer roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + roomId));
        RoomResponseDTO roomResponseDTO = roomMapper.toDto(room);
        AddressResponseDTO addressResponseDTO = addressService.getAddressById(room.getAddress().getAddressId());
        roomResponseDTO.setAddressId(addressResponseDTO.getAddressId());
        return roomResponseDTO;
    }

    public List<RoomResponseDTO> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(room -> {
                    RoomResponseDTO roomResponseDTO = roomMapper.toDto(room);
                    AddressResponseDTO addressResponseDTO = addressService.getAddressById(room.getAddress().getAddressId());
                    roomResponseDTO.setAddressId(addressResponseDTO.getAddressId());
                    return roomResponseDTO;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteRoom(Integer roomId) {
        if (!roomRepository.existsById(roomId)) {
            throw new RuntimeException("Room not found with id: " + roomId);
        }
        roomRepository.deleteById(roomId);
    }

    // Tìm kiếm theo provinceName
    public List<RoomResponseDTO> findByProvinceName(String provinceName) {
        if (provinceName == null || provinceName.trim().isEmpty()) {
            return getAllRooms();
        }
        return roomRepository.findByProvinceName(provinceName).stream()
                .map(room -> {
                    RoomResponseDTO roomResponseDTO = roomMapper.toDto(room);
                    AddressResponseDTO addressResponseDTO = addressService.getAddressById(room.getAddress().getAddressId());
                    roomResponseDTO.setAddressId(addressResponseDTO.getAddressId());
                    return roomResponseDTO;
                })
                .collect(Collectors.toList());
    }

    // Tìm kiếm theo districtName
    public List<RoomResponseDTO> findByWardName(String wardName) {
        if (wardName == null || wardName.trim().isEmpty()) {
            return getAllRooms();
        }
        return roomRepository.findByWardName(wardName).stream()
                .map(room -> {
                    RoomResponseDTO roomResponseDTO = roomMapper.toDto(room);
                    AddressResponseDTO addressResponseDTO = addressService.getAddressById(room.getAddress().getAddressId());
                    roomResponseDTO.setAddressId(addressResponseDTO.getAddressId());
                    return roomResponseDTO;
                })
                .collect(Collectors.toList());
    }

    // Tìm kiếm theo districtId
    public List<RoomResponseDTO> findByDistrictId(Integer districtId) {
        if (districtId == null) {
            return getAllRooms();
        }
        return roomRepository.findByDistrictId(districtId).stream()
                .map(room -> {
                    RoomResponseDTO roomResponseDTO = roomMapper.toDto(room);
                    AddressResponseDTO addressResponseDTO = addressService.getAddressById(room.getAddress().getAddressId());
                    roomResponseDTO.setAddressId(addressResponseDTO.getAddressId());
                    return roomResponseDTO;
                })
                .collect(Collectors.toList());
    }
    // Tìm kiếm theo districtName
    public List<RoomResponseDTO> findByDistrictName(String districtName) {
        if (districtName == null || districtName.trim().isEmpty()) {
            return getAllRooms();
        }
        return roomRepository.findByDistrictName(districtName).stream()
                .map(room -> {
                    RoomResponseDTO roomResponseDTO = roomMapper.toDto(room);
                    AddressResponseDTO addressResponseDTO = addressService.getAddressById(room.getAddress().getAddressId());
                    roomResponseDTO.setAddressId(addressResponseDTO.getAddressId());
                    return roomResponseDTO;
                })
                .collect(Collectors.toList());
    }

    // Tìm kiếm theo districtId
    public List<RoomResponseDTO> findByWardId(Integer wardId) {
        if (wardId == null) {
            return getAllRooms();
        }
        return roomRepository.findByWardId(wardId).stream()
                .map(room -> {
                    RoomResponseDTO roomResponseDTO = roomMapper.toDto(room);
                    AddressResponseDTO addressResponseDTO = addressService.getAddressById(room.getAddress().getAddressId());
                    roomResponseDTO.setAddressId(addressResponseDTO.getAddressId());
                    return roomResponseDTO;
                })
                .collect(Collectors.toList());
    }

    // Tìm kiếm theo provinceId
    public List<RoomResponseDTO> findByProvinceId(Integer provinceId) {
        if (provinceId == null) {
            return getAllRooms();
        }
        return roomRepository.findByProvinceId(provinceId).stream()
                .map(room -> {
                    RoomResponseDTO roomResponseDTO = roomMapper.toDto(room);
                    AddressResponseDTO addressResponseDTO = addressService.getAddressById(room.getAddress().getAddressId());
                    roomResponseDTO.setAddressId(addressResponseDTO.getAddressId());
                    return roomResponseDTO;
                })
                .collect(Collectors.toList());
    }

    // Tìm kiếm theo trạng thái isAvailable
    public List<RoomResponseDTO> findByIsAvailable(Integer isAvailable) {
        return roomRepository.findByIsActive(isAvailable).stream()
                .map(room -> {
                    RoomResponseDTO roomResponseDTO = roomMapper.toDto(room);
                    AddressResponseDTO addressResponseDTO = addressService.getAddressById(room.getAddress().getAddressId());
                    roomResponseDTO.setAddressId(addressResponseDTO.getAddressId());
                    return roomResponseDTO;
                })
                .collect(Collectors.toList());
    }
    public List<RoomResponseDTO> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        if (minPrice == null || maxPrice == null) {
            throw new IllegalArgumentException("minPrice and maxPrice cannot be null");
        }
        if (minPrice.compareTo(maxPrice) > 0) {
            throw new IllegalArgumentException("minPrice must be less than or equal to maxPrice");
        }
        return roomRepository.findByPriceBetween(minPrice, maxPrice).stream()
                .map(room -> {
                    RoomResponseDTO roomResponseDTO = roomMapper.toDto(room);
                    AddressResponseDTO addressResponseDTO = addressService.getAddressById(room.getAddress().getAddressId());
                    roomResponseDTO.setAddressId(addressResponseDTO.getAddressId());
                    return roomResponseDTO;
                })
                .collect(Collectors.toList());
    }

    public List<RoomResponseDTO> findByManagerId(Integer id){
        if(id == null){
            throw new RuntimeException("Manager Id is requied");
        }
        return roomRepository.findByManagerManagerId(id).stream()
                .map(room -> {
                    RoomResponseDTO roomResponseDTO = roomMapper.toDto(room);
                    AddressResponseDTO addressResponseDTO = addressService.getAddressById(room.getAddress().getAddressId());
                    roomResponseDTO.setAddressId(addressResponseDTO.getAddressId());
                    return roomResponseDTO;
                })
                .collect(Collectors.toList());
    }

    public String getDiractionUrl(Integer roomId){
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + roomId));
        Address address = room.getAddress();
        CoordinatesDTO coordinatesDTO = new CoordinatesDTO(address.getLatitude(), address.getLongitude());
        String dirationUrl = addressService.getDirectionsUrl(coordinatesDTO, "Driving");

        return dirationUrl;
    }

    public Page<RoomResponseDTO> searchRooms(Integer provinceId, Integer districtId, Integer wardId,
                                             BigDecimal minPrice, BigDecimal maxPrice,
                                             BigDecimal minArea, BigDecimal maxArea, int page) {
        // Kiểm tra minPrice <= maxPrice
        if (minPrice != null && maxPrice != null && minPrice.compareTo(maxPrice) > 0) {
            throw new IllegalArgumentException("minPrice must be less than or equal to maxPrice");
        }
        if (minArea != null && maxArea != null && minArea.compareTo(maxArea) > 0) {
            throw new IllegalArgumentException("minArea must be less than or equal to maxArea");
        }
        // Tạo Pageable với page (bắt đầu từ 0) và kích thước trang là 8
        Pageable pageable = PageRequest.of(page - 1, 8);

        // Gọi repository để lấy kết quả phân trang
        Page<Room> roomPage = roomRepository.searchRooms(provinceId, districtId, wardId, minPrice, maxPrice, maxArea, minArea, pageable);

        // Ánh xạ Page<Room> sang List<RoomResponseDTO>
        List<RoomResponseDTO> roomResponseDTOs = roomPage.getContent().stream()
                .map(room -> {
                    RoomResponseDTO roomResponseDTO = roomMapper.toDto(room);
                    AddressResponseDTO addressResponseDTO = addressService.getAddressById(room.getAddress().getAddressId());
                    roomResponseDTO.setAddressId(addressResponseDTO.getAddressId());
                    return roomResponseDTO;
                })
                .collect(Collectors.toList());

        // Tạo PageImpl để trả về kết quả phân trang
        return new PageImpl<>(roomResponseDTOs, pageable, roomPage.getTotalElements());
    }
}