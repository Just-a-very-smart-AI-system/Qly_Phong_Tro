package com.example.QuanLyPhongTro.Service;

import com.example.QuanLyPhongTro.DTO.Request.CreateRoomUtilityRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomUtilityResponseDTO;
import com.example.QuanLyPhongTro.Entity.Room;
import com.example.QuanLyPhongTro.Entity.RoomUtility;
import com.example.QuanLyPhongTro.Mapper.RoomUtilityMapper;
import com.example.QuanLyPhongTro.Repository.RoomRepository;
import com.example.QuanLyPhongTro.Repository.RoomUtilityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomUtilityService {

    private final RoomUtilityRepository roomUtilityRepository;
    private final RoomRepository roomRepository;
    private final RoomUtilityMapper roomUtilityMapper;

    @Transactional
    public RoomUtilityResponseDTO createRoomUtility(CreateRoomUtilityRequestDTO requestDTO) {
        RoomUtility roomUtility = roomUtilityMapper.toEntity(requestDTO);

        // Gán Room
        if (requestDTO.getRoomId() != null) {
            Room room = roomRepository.findById(requestDTO.getRoomId())
                    .orElseThrow(() -> new RuntimeException("Room not found with id: " + requestDTO.getRoomId()));
            roomUtility.setRoom(room);
        }

        roomUtility = roomUtilityRepository.save(roomUtility);
        return roomUtilityMapper.toDto(roomUtility);
    }

    public RoomUtilityResponseDTO getRoomUtilityById(Integer utilityId) {
        return roomUtilityRepository.findById(utilityId)
                .map(roomUtilityMapper::toDto)
                .orElseThrow(() -> new RuntimeException("RoomUtility not found with id: " + utilityId));
    }

    public List<RoomUtilityResponseDTO> getAllRoomUtilities() {
        return roomUtilityRepository.findAll().stream()
                .map(roomUtilityMapper::toDto)
                .toList();
    }

    @Transactional
    public void deleteRoomUtility(Integer utilityId) {
        if (!roomUtilityRepository.existsById(utilityId)) {
            throw new RuntimeException("RoomUtility not found with id: " + utilityId);
        }
        roomUtilityRepository.deleteById(utilityId);
    }
}
