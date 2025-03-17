package com.example.QuanLyPhongTro.DTO.Response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RoomMediaResponseDTO {
    private Integer mediaId;
    private Integer roomId;
    private String mediaType;
    private String mediaUrl;
    private LocalDateTime uploadedAt;
}
