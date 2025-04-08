package com.example.QuanLyPhongTro.Service;

import com.example.QuanLyPhongTro.DTO.Request.CreateRoomViewRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomViewResponseDTO;
import com.example.QuanLyPhongTro.Entity.Room;
import com.example.QuanLyPhongTro.Entity.RoomView;
import com.example.QuanLyPhongTro.Entity.User;
import com.example.QuanLyPhongTro.Mapper.RoomViewMapper;
import com.example.QuanLyPhongTro.Repository.RoomRepository;
import com.example.QuanLyPhongTro.Repository.RoomViewRepository;
import com.example.QuanLyPhongTro.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomViewService {

    private final RoomViewRepository roomViewRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final RoomViewMapper roomViewMapper;

    @Transactional
    public RoomViewResponseDTO createRoomView(CreateRoomViewRequestDTO requestDTO) {
        RoomView roomView = new RoomView();

        // Gán User
        if (requestDTO.getUserId() != null) {
            User user = userRepository.findById(requestDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + requestDTO.getUserId()));
            roomView.setUser(user);
        }

        // Gán Room
        if (requestDTO.getRoomId() != null) {
            Room room = roomRepository.findById(requestDTO.getRoomId())
                    .orElseThrow(() -> new RuntimeException("Room not found with id: " + requestDTO.getRoomId()));
            roomView.setRoom(room);
        }
        roomView.setViewedAt(LocalDateTime.now());
        roomView = roomViewRepository.save(roomView);
        return roomViewMapper.toDto(roomView);
    }

    public RoomViewResponseDTO getRoomViewById(Integer viewId) {
        return roomViewRepository.findById(viewId)
                .map(roomViewMapper::toDto)
                .orElseThrow(() -> new RuntimeException("RoomView not found with id: " + viewId));
    }

    public List<RoomViewResponseDTO> getAllRoomViews() {
        return roomViewRepository.findAll().stream()
                .map(roomViewMapper::toDto)
                .toList();
    }

    @Transactional
    public void deleteRoomView(Integer viewId) {
        if (!roomViewRepository.existsById(viewId)) {
            throw new RuntimeException("RoomView not found with id: " + viewId);
        }
        roomViewRepository.deleteById(viewId);
    }
}