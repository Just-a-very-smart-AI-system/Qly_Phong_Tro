package com.example.QuanLyPhongTro.Service;

import com.example.QuanLyPhongTro.DTO.Request.CreateFavoriteRoomRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.FavoriteRoomResponseDTO;
import com.example.QuanLyPhongTro.Entity.FavoriteRoom;
import com.example.QuanLyPhongTro.Entity.Room;
import com.example.QuanLyPhongTro.Entity.User;
import com.example.QuanLyPhongTro.Mapper.FavoriteRoomMapper;
import com.example.QuanLyPhongTro.Repository.FavoriteRoomRepository;
import com.example.QuanLyPhongTro.Repository.RoomRepository;
import com.example.QuanLyPhongTro.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteRoomService {

    private final FavoriteRoomRepository favoriteRoomRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final FavoriteRoomMapper favoriteRoomMapper;

    @Transactional
    public FavoriteRoomResponseDTO createFavoriteRoom(CreateFavoriteRoomRequestDTO requestDTO) {
        FavoriteRoom favoriteRoom = new FavoriteRoom();

        // Gán User
        if (requestDTO.getUserId() != null) {
            User user = userRepository.findById(requestDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + requestDTO.getUserId()));
            favoriteRoom.setUser(user);
        }

        // Gán Room
        if (requestDTO.getRoomId() != null) {
            Room room = roomRepository.findById(requestDTO.getRoomId())
                    .orElseThrow(() -> new RuntimeException("Room not found with id: " + requestDTO.getRoomId()));
            favoriteRoom.setRoom(room);
        }
        favoriteRoom.setAddedAt(LocalDateTime.now());
        favoriteRoom = favoriteRoomRepository.save(favoriteRoom);
        return favoriteRoomMapper.toDto(favoriteRoom);
    }

    public FavoriteRoomResponseDTO getFavoriteRoomById(Integer favoriteId) {
        return favoriteRoomRepository.findById(favoriteId)
                .map(favoriteRoomMapper::toDto)
                .orElseThrow(() -> new RuntimeException("FavoriteRoom not found with id: " + favoriteId));
    }

    public List<FavoriteRoomResponseDTO> getAllFavoriteRooms() {
        return favoriteRoomRepository.findAll().stream()
                .map(favoriteRoomMapper::toDto)
                .toList();
    }

    @Transactional
    public void deleteFavoriteRoom(Integer favoriteId) {
        if (!favoriteRoomRepository.existsById(favoriteId)) {
            throw new RuntimeException("FavoriteRoom not found with id: " + favoriteId);
        }
        favoriteRoomRepository.deleteById(favoriteId);
    }
}