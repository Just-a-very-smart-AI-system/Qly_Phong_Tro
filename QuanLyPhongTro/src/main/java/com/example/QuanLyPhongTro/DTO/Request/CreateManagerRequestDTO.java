package com.example.QuanLyPhongTro.DTO.Request;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class CreateManagerRequestDTO {
    @NotBlank(message = "Username is required")
    @Size(max = 50, message = "Username must not exceed 50 characters")
    private String userName;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 255, message = "Password must be between 6 and 255 characters")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @Size(max = 15, message = "Phone must not exceed 15 characters")
    private String phone;

    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;

    @Size(max = 20, message = "CMND must not exceed 20 characters")
    private String cmnd;

    @NotBlank(message = "Role is required")
    @Size(max = 100, message = "Role must not exceed 50 characters")
    private String role;

    @Size(max = 100, message = "Extra contact must not exceed 100 characters")
    private String extraContact;

    private LocalDateTime createdAt;
}
