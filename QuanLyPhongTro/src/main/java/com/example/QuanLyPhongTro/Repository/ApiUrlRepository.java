package com.example.QuanLyPhongTro.Repository;

import com.example.QuanLyPhongTro.Entity.ApiUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApiUrlRepository extends JpaRepository<ApiUrl, Long> {
    List<ApiUrl> findAll();
    List<ApiUrl> findByUrl(String url);
    boolean existsByUrlAndRole(String url, ApiUrl.Role role);
}