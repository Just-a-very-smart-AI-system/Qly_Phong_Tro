package com.example.QuanLyPhongTro.Controller;


import com.example.QuanLyPhongTro.DTO.Request.CreateRoomMediaRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomMediaResponseDTO;
import com.example.QuanLyPhongTro.Service.RoomMediaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room-media")
@RequiredArgsConstructor
public class RoomMediaController {

    private final RoomMediaService roomMediaService;

    @PostMapping
    public ResponseEntity<RoomMediaResponseDTO> createRoomMedia(@Valid @RequestBody CreateRoomMediaRequestDTO requestDTO) {
        RoomMediaResponseDTO response = roomMediaService.createRoomMedia(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{mediaId}")
    public ResponseEntity<RoomMediaResponseDTO> getRoomMediaById(@PathVariable Integer mediaId) {
        RoomMediaResponseDTO response = roomMediaService.getRoomMediaById(mediaId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RoomMediaResponseDTO>> getAllRoomMedia() {
        List<RoomMediaResponseDTO> response = roomMediaService.getAllRoomMedia();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{mediaId}")
    public ResponseEntity<Void> deleteRoomMedia(@PathVariable Integer mediaId) {
        roomMediaService.deleteRoomMedia(mediaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}