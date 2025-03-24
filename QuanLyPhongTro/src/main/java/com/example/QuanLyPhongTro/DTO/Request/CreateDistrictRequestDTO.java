package com.example.QuanLyPhongTro.DTO.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class CreateDistrictRequestDTO {
    @NotNull(message = "District Id is requited")
    private Integer districtId;

    @NotNull(message = "Province Id is requited")
    private Integer provinceId;
    @NotBlank(message = "District name is required")
    @Size(max = 100, message = "District name must not exceed 100 characters")
    private String districtName;
}