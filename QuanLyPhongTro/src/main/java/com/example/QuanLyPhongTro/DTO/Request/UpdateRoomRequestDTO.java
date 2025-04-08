package com.example.QuanLyPhongTro.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class UpdateRoomRequestDTO {

//    @Size(max = 65535, message = "Description must not exceed 65535 characters")
    private String description;

    private BigDecimal price;
    private BigDecimal area;
    private Integer maxOccupants;
//    @NotBlank(message = "Status is required")
    private Integer isActive;
}