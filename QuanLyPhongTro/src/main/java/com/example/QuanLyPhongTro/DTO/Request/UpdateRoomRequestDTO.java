package com.example.QuanLyPhongTro.DTO.Request;

import lombok.Data;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UpdateRoomRequestDTO {
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;

    @Size(max = 65535, message = "Description must not exceed 65535 characters")
    private String description;

    private BigDecimal price;
    private BigDecimal area;
    private Integer maxOccupants;
    private LocalDateTime updatedAt;
    private Integer addressId;
}