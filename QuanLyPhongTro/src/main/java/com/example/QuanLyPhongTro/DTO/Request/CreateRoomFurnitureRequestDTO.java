package com.example.QuanLyPhongTro.DTO.Request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Data
public class CreateRoomFurnitureRequestDTO {
    private Integer roomId;

    private List<String> furnitureName;
}