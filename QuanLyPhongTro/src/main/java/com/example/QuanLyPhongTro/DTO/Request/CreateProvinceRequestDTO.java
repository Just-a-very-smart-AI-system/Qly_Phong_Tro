package com.example.QuanLyPhongTro.DTO.Request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class CreateProvinceRequestDTO {
    @NotBlank(message = "Province name is required")
    @Size(max = 100, message = "Province name must not exceed 100 characters")
    private String provinceName;
}