package com.example.QuanLyPhongTro.Controller;


import com.example.QuanLyPhongTro.Configuration.StoreApi;
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

    @StoreApi(roles = {"ROLE_ADMIN"})
    @PostMapping("/create")
    public ResponseEntity<DistrictResponseDTO> createDistrict(@Valid @RequestBody CreateDistrictRequestDTO requestDTO) {
        DistrictResponseDTO response = districtService.createDistrict(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @StoreApi(roles = {"ROLE_ADMIN"})
    @GetMapping("/getById/{districtId}")
    public ResponseEntity<DistrictResponseDTO> getDistrictById(@PathVariable Integer districtId) {
        DistrictResponseDTO response = districtService.getDistrictById(districtId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @StoreApi(roles = {"ROLE_ADMIN"})
    @GetMapping("/getAll")
    public ResponseEntity<List<DistrictResponseDTO>> getAllDistricts() {
        List<DistrictResponseDTO> response = districtService.getAllDistricts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"})
    @GetMapping("/getByProvince/{id}")
    public ResponseEntity<List<DistrictResponseDTO>> getByProvince(@PathVariable Integer id) {
        List<DistrictResponseDTO> response = districtService.getAllByProvince(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @StoreApi(roles = {"ROLE_ADMIN"})
    @DeleteMapping("/delete/{districtId}")
    public ResponseEntity<Void> deleteDistrict(@PathVariable Integer districtId) {
        districtService.deleteDistrict(districtId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
