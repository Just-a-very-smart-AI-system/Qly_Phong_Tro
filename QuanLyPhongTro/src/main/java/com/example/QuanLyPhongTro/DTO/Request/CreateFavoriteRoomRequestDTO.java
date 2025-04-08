package com.example.QuanLyPhongTro.DTO.Request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateFavoriteRoomRequestDTO {
    private Integer userId;
    private Integer roomId;
}