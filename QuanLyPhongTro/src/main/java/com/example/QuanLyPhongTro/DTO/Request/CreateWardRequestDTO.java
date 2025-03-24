package com.example.QuanLyPhongTro.DTO.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class CreateWardRequestDTO {
    @NotNull(message = "Ward Id is requited")
    private Integer wardId;

    @NotNull(message = "District Id is requited")
    private Integer districtId;
    @NotBlank(message = "Ward name is required")
    @Size(max = 100, message = "Ward name must not exceed 100 characters")
    private String wardName;
}