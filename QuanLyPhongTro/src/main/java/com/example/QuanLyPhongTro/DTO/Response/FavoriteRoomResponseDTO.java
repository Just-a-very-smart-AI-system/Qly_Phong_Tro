package com.example.QuanLyPhongTro.DTO.Response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FavoriteRoomResponseDTO {
    private Integer favoriteId;
    private Integer userId;
    private Integer roomId;
    private LocalDateTime addedAt;
}