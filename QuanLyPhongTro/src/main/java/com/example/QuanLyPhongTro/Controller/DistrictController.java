package com.example.QuanLyPhongTro.Controller;


import com.example.QuanLyPhongTro.DTO.Request.CreateDistrictRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.DistrictResponseDTO;
import com.example.QuanLyPhongTro.Service.DistrictService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictService districtService;

    @PostMapping
    public ResponseEntity<DistrictResponseDTO> createDistrict(@Valid @RequestBody CreateDistrictRequestDTO requestDTO) {
        DistrictResponseDTO response = districtService.createDistrict(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{districtId}")
    public ResponseEntity<DistrictResponseDTO> getDistrictById(@PathVariable Integer districtId) {
        DistrictResponseDTO response = districtService.getDistrictById(districtId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DistrictResponseDTO>> getAllDistricts() {
        List<DistrictResponseDTO> response = districtService.getAllDistricts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{districtId}")
    public ResponseEntity<Void> deleteDistrict(@PathVariable Integer districtId) {
        districtService.deleteDistrict(districtId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
