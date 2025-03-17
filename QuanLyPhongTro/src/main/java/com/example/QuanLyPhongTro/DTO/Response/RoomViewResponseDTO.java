package com.example.QuanLyPhongTro.DTO.Response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RoomViewResponseDTO {
    private Integer viewId;
    private Integer userId;
    private Integer roomId;
    private LocalDateTime viewedAt;
}
