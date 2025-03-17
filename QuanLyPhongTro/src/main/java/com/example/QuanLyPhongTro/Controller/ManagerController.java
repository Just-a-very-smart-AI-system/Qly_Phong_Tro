package com.example.QuanLyPhongTro.Controller;


import com.example.QuanLyPhongTro.DTO.Request.CreateManagerRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.ManagerResponseDTO;
import com.example.QuanLyPhongTro.Service.ManagerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/managers")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping
    public ResponseEntity<ManagerResponseDTO> createManager(@Valid @RequestBody CreateManagerRequestDTO requestDTO) {
        ManagerResponseDTO response = managerService.createManager(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{managerId}")
    public ResponseEntity<ManagerResponseDTO> updateManager(@PathVariable Integer managerId, @Valid @RequestBody CreateManagerRequestDTO requestDTO) {
        ManagerResponseDTO response = managerService.updateManager(managerId, requestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{managerId}")
    public ResponseEntity<ManagerResponseDTO> getManagerById(@PathVariable Integer managerId) {
        ManagerResponseDTO response = managerService.getManagerById(managerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ManagerResponseDTO>> getAllManagers() {
        List<ManagerResponseDTO> response = managerService.getAllManagers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{managerId}")
    public ResponseEntity<Void> deleteManager(@PathVariable Integer managerId) {
        managerService.deleteManager(managerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}