package com.example.QuanLyPhongTro.Service;

import com.example.QuanLyPhongTro.DTO.Request.CreateRoomMediaRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomMediaResponseDTO;
import com.example.QuanLyPhongTro.DTO.Response.UploadResponse;
import com.example.QuanLyPhongTro.Entity.Room;
import com.example.QuanLyPhongTro.Entity.RoomMedia;
import com.example.QuanLyPhongTro.Mapper.RoomMediaMapper;
import com.example.QuanLyPhongTro.Repository.RoomMediaRepository;
import com.example.QuanLyPhongTro.Repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomMediaService {

    private final RoomMediaRepository roomMediaRepository;
    private final GoogleCloudService googleCloudService;
    private final RoomRepository roomRepository;
    private final RoomMediaMapper roomMediaMapper;

    @Transactional
    public List<RoomMediaResponseDTO> createRoomMedia(CreateRoomMediaRequestDTO requestDTO) {

        // Kiểm tra danh sách tệp
        List<MultipartFile> files = requestDTO.getFileUrl();
        if (files == null || files.isEmpty()) {
            throw new IllegalArgumentException("Danh sách tệp không được rỗng");
        }

        // Gọi GoogleCloudService để tải lên các tệp
        ResponseEntity<List<UploadResponse>> uploadResponse = googleCloudService.handleFileUpload(files);
        if (uploadResponse.getStatusCode() != org.springframework.http.HttpStatus.OK || uploadResponse.getBody() == null) {
            throw new RuntimeException(uploadResponse.getBody().getFirst().getMessage());
        }

        List<RoomMediaResponseDTO> responseDTOs = new ArrayList<>();
        List<UploadResponse> uploadResults = uploadResponse.getBody();

        // Xử lý từng kết quả tải lên
        for (UploadResponse uploadResult : uploadResults) {
            if (!uploadResult.getMessage().startsWith("Upload thành công")) {
                continue; // Bỏ qua các tệp thất bại
            }

            // Tạo RoomMedia
            RoomMedia roomMedia = new RoomMedia();
            roomMedia.setMediaUrl(uploadResult.getUrl());
            roomMedia.setUploadedAt(requestDTO.getUploadedAt() != null ? requestDTO.getUploadedAt() : LocalDateTime.now());

            // Gán Room
            if (requestDTO.getRoomId() != null) {
                Room room = roomRepository.findById(requestDTO.getRoomId())
                        .orElseThrow(() -> {
                            return new RuntimeException("Room not found with id: " + requestDTO.getRoomId());
                        });
                roomMedia.setRoom(room);
            }

            // Lưu RoomMedia
            roomMedia = roomMediaRepository.save(roomMedia);

            // Chuyển đổi sang DTO
            responseDTOs.add(roomMediaMapper.toDto(roomMedia));
        }

        if (responseDTOs.isEmpty()) {
            throw new RuntimeException("Không có tệp nào được tải lên thành công");
        }

        return responseDTOs;
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
    public List<RoomMediaResponseDTO> getAllRoomByRoomId(Long roomId) {
        return roomMediaRepository.findAllByRoomRoomId(roomId).stream()
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
