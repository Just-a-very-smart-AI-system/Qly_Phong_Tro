package com.example.QuanLyPhongTro.DTO.Response;

import lombok.Data;
import java.util.List;

@Data
public class RoomFurnitureResponseDTO {
    private Integer roomId;
    private List<String> utility;
}