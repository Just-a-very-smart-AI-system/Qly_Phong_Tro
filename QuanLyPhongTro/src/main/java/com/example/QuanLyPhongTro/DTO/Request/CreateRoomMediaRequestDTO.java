package com.example.QuanLyPhongTro.DTO.Request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class CreateRoomMediaRequestDTO {
    private Integer roomId;
    private String mediaType; // Có thể dùng enum hoặc String tùy theo thiết kế
    @NotBlank(message = "Media URL is required")
    @Size(max = 255, message = "Media URL must not exceed 255 characters")
    private String mediaUrl;
    private LocalDateTime uploadedAt;
}