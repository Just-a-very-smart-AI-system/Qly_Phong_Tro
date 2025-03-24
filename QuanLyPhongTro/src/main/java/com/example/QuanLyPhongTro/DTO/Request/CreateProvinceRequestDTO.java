package com.example.QuanLyPhongTro.DTO.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class CreateProvinceRequestDTO {
    @NotNull(message = "Province Id is required")
    private Integer provinceId;

    @NotBlank(message = "Province name is required")
    @Size(max = 100, message = "Province name must not exceed 100 characters")
    private String provinceName;
}