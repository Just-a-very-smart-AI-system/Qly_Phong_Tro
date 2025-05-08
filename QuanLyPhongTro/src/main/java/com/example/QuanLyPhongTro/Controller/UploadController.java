package com.example.QuanLyPhongTro.Controller;

import com.example.QuanLyPhongTro.Configuration.StoreApi;
import com.example.QuanLyPhongTro.DTO.Response.UploadResponse;
import com.example.QuanLyPhongTro.Service.GoogleCloudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/gg-cloud")
public class UploadController {
    private final GoogleCloudService googleCloudService;

    public UploadController(GoogleCloudService googleCloudService) {
        this.googleCloudService = googleCloudService;
    }

    @StoreApi(roles = {"ROLE_ADMIN"})
    @PostMapping("/upload")
    public ResponseEntity<List<UploadResponse>> uploadFile(@RequestParam("files") List<MultipartFile> files) {
        return googleCloudService.handleFileUpload(files);
    }
}

