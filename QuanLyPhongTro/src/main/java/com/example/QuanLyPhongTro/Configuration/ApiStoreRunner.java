package com.example.QuanLyPhongTro.Configuration;

import com.example.QuanLyPhongTro.Entity.ApiUrl;
import com.example.QuanLyPhongTro.Repository.ApiUrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ApiStoreRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ApiStoreRunner.class);

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    private ApiUrlRepository apiUrlRepository;

    @Override
    public void run(String... args) throws Exception {
        // Lấy tất cả bản ghi hiện có từ bảng api_url
        List<ApiUrl> existingApiUrls = apiUrlRepository.findAll();
        Map<String, ApiUrl> apiUrlMap = existingApiUrls.stream()
                .collect(Collectors.toMap(
                        apiUrl -> apiUrl.getUrl() + ":" + apiUrl.getRole(),
                        Function.identity(),
                        (existing, replacement) -> existing // Giữ bản ghi đầu tiên nếu có trùng lặp
                ));

        List<ApiUrl> apiUrlsToSave = new ArrayList<>();

        requestMappingHandlerMapping.getHandlerMethods().forEach((requestMappingInfo, handlerMethod) -> {
            // Kiểm tra nếu method có annotation @StoreApi
            if (handlerMethod.hasMethodAnnotation(StoreApi.class)) {
                StoreApi storeApi = handlerMethod.getMethodAnnotation(StoreApi.class);
                String[] roles = storeApi.roles();

                // Lấy URL từ RequestMappingInfo
                String url = requestMappingInfo.getPatternValues().stream()
                        .findFirst()
                        .orElse(null);

                if (url == null || url.isEmpty()) {
                    logger.warn("URL is null or empty for method: {}", handlerMethod.getMethod().getName());
                    return;
                }

                // Lưu từng vai trò cho URL
                if (roles != null && roles.length > 0) {
                    for (String role : roles) {
                        try {
                            ApiUrl.Role enumRole = ApiUrl.Role.valueOf(role);
                            String urlRoleKey = url + ":" + enumRole;

                            // Kiểm tra nếu cặp {url, role} đã tồn tại
                            if (!apiUrlMap.containsKey(urlRoleKey)) {
                                ApiUrl apiUrl = new ApiUrl();
                                apiUrl.setUrl(url);
                                apiUrl.setRole(enumRole);
                                apiUrlsToSave.add(apiUrl);
                                apiUrlMap.put(urlRoleKey, apiUrl);
                            }
                        } catch (IllegalArgumentException e) {
                            logger.error("Invalid role: {} for URL: {}", role, url);
                        }
                    }
                } else {
                    logger.warn("No roles specified for URL: {}", url);
                }
            }
        });

        // Lưu vào database
        if (!apiUrlsToSave.isEmpty()) {
            apiUrlRepository.saveAll(apiUrlsToSave);
            logger.info("Saved {} new API URL entries to the database", apiUrlsToSave.size());
        } else {
            logger.info("No new API URL entries to save");
        }
    }
}