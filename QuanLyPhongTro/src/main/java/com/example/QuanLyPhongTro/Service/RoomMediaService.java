package com.example.QuanLyPhongTro.Service;

import com.example.QuanLyPhongTro.DTO.Request.CreateRoomMediaRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomMediaResponseDTO;
import com.example.QuanLyPhongTro.Entity.Room;
import com.example.QuanLyPhongTro.Entity.RoomMedia;
import com.example.QuanLyPhongTro.Mapper.RoomMediaMapper;
import com.example.QuanLyPhongTro.Repository.RoomMediaRepository;
import com.example.QuanLyPhongTro.Repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomMediaService {

    private final RoomMediaRepository roomMediaRepository;
    private final RoomRepository roomRepository;
    private final RoomMediaMapper roomMediaMapper;

    @Transactional
    public RoomMediaResponseDTO createRoomMedia(CreateRoomMediaRequestDTO requestDTO) {
        RoomMedia roomMedia = roomMediaMapper.toEntity(requestDTO);
        roomMedia.setUploadedAt(requestDTO.getUploadedAt() != null ? requestDTO.getUploadedAt() : LocalDateTime.now());

        // Gán Room
        if (requestDTO.getRoomId() != null) {
            Room room = roomRepository.findById(requestDTO.getRoomId())
                    .orElseThrow(() -> new RuntimeException("Room not found with id: " + requestDTO.getRoomId()));
            roomMedia.setRoom(room);
        }

        roomMedia = roomMediaRepository.save(roomMedia);
        return roomMediaMapper.toDto(roomMedia);
    }

    public RoomMediaResponseDTO getRoomMediaById(Integer mediaId) {
        return roomMediaRepository.findById(mediaId)
                .map(roomMediaMapper::toDto)
                .orElseThrow(() -> new RuntimeException("RoomMedia not found with id: " + mediaId));
    }

    public List<RoomMediaResponseDTO> getAllRoomMedia() {
        return roomMediaRepository.findAll().stream()
                .map(roomMediaMapper::toDto)
                .toList();
    }

    @Transactional
    public void deleteRoomMedia(Integer mediaId) {
        if (!roomMediaRepository.existsById(mediaId)) {
            throw new RuntimeException("RoomMedia not found with id: " + mediaId);
        }
        roomMediaRepository.deleteById(mediaId);
    }
}
