package com.example.QuanLyPhongTro.Controller;


import com.example.QuanLyPhongTro.DTO.Request.CreateManagerRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.ManagerResponseDTO;
import com.example.QuanLyPhongTro.Service.ManagerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/managers")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping("/create")
    public ResponseEntity<ManagerResponseDTO> createManager(@Valid @RequestBody CreateManagerRequestDTO requestDTO) {
        ManagerResponseDTO response = managerService.createManager(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("update/{managerId}")
    public ResponseEntity<ManagerResponseDTO> updateManager(@PathVariable Integer managerId, @Valid @RequestBody CreateManagerRequestDTO requestDTO) {
        ManagerResponseDTO response = managerService.updateManager(managerId, requestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MANAGER, ADMIN')")
    @GetMapping("getById/{managerId}")
    public ResponseEntity<ManagerResponseDTO> getManagerById(@PathVariable Integer managerId) {
        ManagerResponseDTO response = managerService.getManagerById(managerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ManagerResponseDTO>> getAllManagers() {
        List<ManagerResponseDTO> response = managerService.getAllManagers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete/{managerId}")
    public ResponseEntity<Void> deleteManager(@PathVariable Integer managerId) {
        managerService.deleteManager(managerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}