package com.example.QuanLyPhongTro.Controller;


import com.example.QuanLyPhongTro.Configuration.StoreApi;
import com.example.QuanLyPhongTro.DTO.Request.CreateWardRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.WardResponseDTO;
import com.example.QuanLyPhongTro.Service.WardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wards")
@RequiredArgsConstructor
public class WardController {

    private final WardService wardService;

    @StoreApi(roles = {"ROLE_ADMIN"})
    @PostMapping("/create")
    public ResponseEntity<WardResponseDTO> createWard(@Valid @RequestBody CreateWardRequestDTO requestDTO) {
        WardResponseDTO response = wardService.createWard(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @StoreApi(roles = {"ROLE_ADMIN"})
    @GetMapping("/getById/{wardId}")
    public ResponseEntity<WardResponseDTO> getWardById(@PathVariable Integer wardId) {
        WardResponseDTO response = wardService.getWardById(wardId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @StoreApi(roles = {"ROLE_ADMIN"})
    @GetMapping("/getAll")
    public ResponseEntity<List<WardResponseDTO>> getAllWards() {
        List<WardResponseDTO> response = wardService.getAllWards();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @StoreApi(roles = {"ROLE_ADMIN"})
    @GetMapping("/getByDistrict/{districtId}")
    public ResponseEntity<List<WardResponseDTO>> getByDistrict(@PathVariable Integer districtId) {
        List<WardResponseDTO> response = wardService.getByDistrict(districtId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @StoreApi(roles = {"ROLE_ADMIN"})
    @DeleteMapping("/delete/{wardId}")
    public ResponseEntity<Void> deleteWard(@PathVariable Integer wardId) {
        wardService.deleteWard(wardId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}