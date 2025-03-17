package com.example.QuanLyPhongTro.DTO.Response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserResponseDTO {
    private Integer userId;
    private String userName;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private Integer addressId;
}