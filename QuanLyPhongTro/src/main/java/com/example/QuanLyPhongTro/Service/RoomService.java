package com.example.QuanLyPhongTro.Service;

import com.example.QuanLyPhongTro.DTO.Request.CreateRoomRequestDTO;
import com.example.QuanLyPhongTro.DTO.Request.UpdateRoomRequestDTO;
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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final ManagerRepository managerRepository;
    private final AddressRepository addressRepository;
    private final RoomMapper roomMapper;

    @Transactional
    public RoomResponseDTO createRoom(CreateRoomRequestDTO requestDTO) {
        Room room = roomMapper.toEntity(requestDTO);
        room.setCreatedAt(LocalDateTime.now());
        room.setUpdatedAt(LocalDateTime.now());

        // Gán Manager
        if (requestDTO.getManagerId() != null) {
            Manager manager = managerRepository.findById(requestDTO.getManagerId())
                    .orElseThrow(() -> new RuntimeException("Manager not found with id: " + requestDTO.getManagerId()));
            room.setManager(manager);
        }

        // Gán Address
        if (requestDTO.getAddressId() != null) {
            Address address = addressRepository.findById(requestDTO.getAddressId())
                    .orElseThrow(() -> new RuntimeException("Address not found with id: " + requestDTO.getAddressId()));
            room.setAddress(address);
        }

        room = roomRepository.save(room);
        return roomMapper.toDto(room);
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

        // Cập nhật Address nếu có
        if (requestDTO.getAddressId() != null) {
            Address address = addressRepository.findById(requestDTO.getAddressId())
                    .orElseThrow(() -> new RuntimeException("Address not found with id: " + requestDTO.getAddressId()));
            room.setAddress(address);
        }

        room = roomRepository.save(room);
        return roomMapper.toDto(room);
    }

    public RoomResponseDTO getRoomById(Integer roomId) {
        return roomRepository.findById(roomId)
                .map(roomMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + roomId));
    }

    public List<RoomResponseDTO> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(roomMapper::toDto)
                .toList();
    }

    @Transactional
    public void deleteRoom(Integer roomId) {
        if (!roomRepository.existsById(roomId)) {
            throw new RuntimeException("Room not found with id: " + roomId);
        }
        roomRepository.deleteById(roomId);
    }
}