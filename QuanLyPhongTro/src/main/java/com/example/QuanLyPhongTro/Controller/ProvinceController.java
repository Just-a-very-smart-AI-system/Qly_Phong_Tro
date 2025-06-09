package com.example.QuanLyPhongTro.Controller;

import com.example.QuanLyPhongTro.Configuration.StoreApi;
import com.example.QuanLyPhongTro.DTO.Request.CreateProvinceRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.ProvinceResponseDTO;
import com.example.QuanLyPhongTro.Service.ProvinceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provinces")
@RequiredArgsConstructor
public class ProvinceController {

    private final ProvinceService provinceService;

    @StoreApi(roles = {"ROLE_ADMIN"})
    @PostMapping("/create")
    public ResponseEntity<ProvinceResponseDTO> createProvince(@Valid @RequestBody CreateProvinceRequestDTO requestDTO) {
        ProvinceResponseDTO response = provinceService.createProvince(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @StoreApi(roles = {"ROLE_ADMIN"})
    @GetMapping("/getById/{provinceId}")
    public ResponseEntity<ProvinceResponseDTO> getProvinceById(@PathVariable Integer provinceId) {
        ProvinceResponseDTO response = provinceService.getProvinceById(provinceId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"})
    @GetMapping("/getAll")
    public ResponseEntity<List<ProvinceResponseDTO>> getAllProvinces() {
        List<ProvinceResponseDTO> response = provinceService.getAllProvinces();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @StoreApi(roles = {"ROLE_ADMIN"})
    @DeleteMapping("/delete/{provinceId}")
    public ResponseEntity<Void> deleteProvince(@PathVariable Integer provinceId) {
        provinceService.deleteProvince(provinceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
