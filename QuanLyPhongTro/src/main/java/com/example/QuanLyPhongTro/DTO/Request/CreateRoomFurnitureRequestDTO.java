package com.example.QuanLyPhongTro.DTO.Request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class CreateRoomFurnitureRequestDTO {
    private Integer roomId;
    @NotBlank(message = "Utility name is required")
    @Size(max = 50, message = "Utility name must not exceed 50 characters")
    private String utilityName;
}