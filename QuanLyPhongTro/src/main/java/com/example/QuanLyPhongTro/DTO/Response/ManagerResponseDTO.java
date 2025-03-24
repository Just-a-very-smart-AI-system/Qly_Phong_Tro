package com.example.QuanLyPhongTro.DTO.Response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ManagerResponseDTO {
    private Integer managerId;
    private String userName;
    private String email;
    private String phone;
    private String address;
    private String cmnd;
    private String extraContact;
    private LocalDateTime createdAt;
}