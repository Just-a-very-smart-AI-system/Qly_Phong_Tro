package com.example.QuanLyPhongTro.DTO.Response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AddressResponseDTO {
    private Integer addressId;
    private String streetAddress;
    private String wardAddress;
    private Integer wardId;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}