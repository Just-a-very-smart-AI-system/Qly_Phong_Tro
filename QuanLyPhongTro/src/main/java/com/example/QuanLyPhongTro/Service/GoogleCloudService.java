package com.example.QuanLyPhongTro.Service;

import com.example.QuanLyPhongTro.DTO.Response.UploadResponse;
import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class GoogleCloudService {
    private final Storage storage;

    @Value("${gcp.bucket.name}")
    private String BUCKET_NAME;

    public GoogleCloudService(Storage storage) {
        this.storage = storage;
    }
    private static final List<String> SUPPORTED_CONTENT_TYPES = Arrays.asList(
            "image/jpeg", "image/png", "image/gif",
            "video/mp4", "video/avi", "video/mpeg", "video/quicktime"
    );
    public ResponseEntity<List<UploadResponse>> handleFileUpload(List<MultipartFile> files) {
        try {
            // Kiểm tra danh sách tệp
            if (files == null || files.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonList(new UploadResponse(null, "Danh sách tệp rỗng")));
            }

            List<UploadResponse> responses = new ArrayList<>();

            for (MultipartFile file : files) {
                // Kiểm tra từng tệp
                if (file.isEmpty()) {
                    responses.add(new UploadResponse( null, "Tệp rỗng"));
                    continue;
                }

                if (file.getSize() > 5 * 1024 * 1024) { // Giới hạn 5MB
                    responses.add(new UploadResponse(null, "Tệp quá lớn (>5MB)"));
                    continue;
                }

                String contentType = file.getContentType();
                if (contentType == null || !SUPPORTED_CONTENT_TYPES.contains(contentType)) {
                    responses.add(new UploadResponse(null, "Loại tệp không được hỗ trợ: " + contentType));
                    continue;
                }

                // Tạo tên tệp duy nhất
                String originalFileName = file.getOriginalFilename();
                String fileExtension = originalFileName != null && originalFileName.contains(".")
                        ? originalFileName.substring(originalFileName.lastIndexOf("."))
                        : "";
                String uniqueFileName = UUID.randomUUID() + fileExtension;

                // Tạo BlobId và BlobInfo
                BlobId blobId = BlobId.of(BUCKET_NAME, uniqueFileName);
                BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                        .setContentType(file.getContentType())
                        .build();
                // Tải lên GCS
                storage.createFrom(blobInfo, file.getInputStream());
                // Tạo URL
                String fileUrl = String.format("https://storage.cloud.google.com/%s/%s?authuser=0", BUCKET_NAME, uniqueFileName);
                // Thêm vào danh sách phản hồi
                responses.add(new UploadResponse(fileUrl, "Upload thành công"));
            }

            return ResponseEntity.ok(responses);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonList(new UploadResponse( null, "Upload thất bại: " + e.getMessage())));
        } catch (StorageException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonList(new UploadResponse( null, "Lỗi GCS: " + e.getMessage())));
        }
    }
}
