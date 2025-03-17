package com.example.QuanLyPhongTro.Controller;

import com.example.QuanLyPhongTro.DTO.Request.CreateRoomUtilityRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomUtilityResponseDTO;
import com.example.QuanLyPhongTro.Service.RoomUtilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room-utilities")
@RequiredArgsConstructor
public class RoomUtilityController {

    private final RoomUtilityService roomUtilityService;

    @PostMapping
    public ResponseEntity<RoomUtilityResponseDTO> createRoomUtility(@Valid @RequestBody CreateRoomUtilityRequestDTO requestDTO) {
        RoomUtilityResponseDTO response = roomUtilityService.createRoomUtility(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{utilityId}")
    public ResponseEntity<RoomUtilityResponseDTO> getRoomUtilityById(@PathVariable Integer utilityId) {
        RoomUtilityResponseDTO response = roomUtilityService.getRoomUtilityById(utilityId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RoomUtilityResponseDTO>> getAllRoomUtilities() {
        List<RoomUtilityResponseDTO> response = roomUtilityService.getAllRoomUtilities();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{utilityId}")
    public ResponseEntity<Void> deleteRoomUtility(@PathVariable Integer utilityId) {
        roomUtilityService.deleteRoomUtility(utilityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}