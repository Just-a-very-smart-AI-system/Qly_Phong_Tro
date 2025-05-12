package com.example.QuanLyPhongTro.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class GoogleCloudConfig {
    private static final Logger logger = LoggerFactory.getLogger(GoogleCloudConfig.class);

    @Bean
    public Storage storage() throws IOException {
        logger.info("Đang khởi tạo Storage bean...");
        InputStream credentialsStream = getClass().getClassLoader()
                .getResourceAsStream("gcp-credentials.json");
        if (credentialsStream == null) {
            logger.error("Không tìm thấy QuanLyPhongTro/src/main/resources/gcp-credentials.json");
            throw new IOException("Tệp credentials.json không tìm thấy trong resources");
        }
        GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);
        return StorageOptions.newBuilder()
                .setCredentials(credentials)
                .build()
                .getService();
    }
}
//test n8n