package com.example.QuanLyPhongTro.DTO.Request;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Data
public class UpdateUserRequestDTO {
    @Size(max = 50, message = "Username must not exceed 50 characters")
    private String userName;

    @Size(min = 6, max = 255, message = "Password must be between 6 and 255 characters")
    private String password;

    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @Size(max = 15, message = "Phone must not exceed 15 characters")
    private String phone;

    private String role;

    private Integer addressId;
}