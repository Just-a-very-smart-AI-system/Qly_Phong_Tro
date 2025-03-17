package com.example.QuanLyPhongTro.DTO.Request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class CreateDistrictRequestDTO {
    private Integer provinceId;
    @NotBlank(message = "District name is required")
    @Size(max = 100, message = "District name must not exceed 100 characters")
    private String districtName;
}