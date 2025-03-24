package com.example.QuanLyPhongTro.Controller;

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

    @PostMapping("/create")
    public ResponseEntity<ProvinceResponseDTO> createProvince(@Valid @RequestBody CreateProvinceRequestDTO requestDTO) {
        ProvinceResponseDTO response = provinceService.createProvince(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{provinceId}")
    public ResponseEntity<ProvinceResponseDTO> getProvinceById(@PathVariable Integer provinceId) {
        ProvinceResponseDTO response = provinceService.getProvinceById(provinceId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProvinceResponseDTO>> getAllProvinces() {
        List<ProvinceResponseDTO> response = provinceService.getAllProvinces();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{provinceId}")
    public ResponseEntity<Void> deleteProvince(@PathVariable Integer provinceId) {
        provinceService.deleteProvince(provinceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
