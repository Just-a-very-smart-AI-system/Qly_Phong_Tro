package com.example.QuanLyPhongTro.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoomMediaRequestDTO {
    private Integer roomId;

    @NotBlank(message = "Media URL is required")
    private List<MultipartFile> fileUrl;
    private LocalDateTime uploadedAt;

}