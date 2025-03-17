package com.example.QuanLyPhongTro.DTO.Response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class RoomResponseDTO {
    private Integer roomId;
    private Integer managerId;
    private String title;
    private String description;
    private BigDecimal price;
    private BigDecimal area;
    private Integer maxOccupants;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer addressId;
}