package com.example.QuanLyPhongTro.Controller;


import com.example.QuanLyPhongTro.Configuration.StoreApi;
import com.example.QuanLyPhongTro.DTO.Request.CreateRoomMediaRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomMediaResponseDTO;
import com.example.QuanLyPhongTro.Service.RoomMediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/room-media")
@RequiredArgsConstructor
public class RoomMediaController {

    private final RoomMediaService roomMediaService;

    @PostMapping("/create")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<List<RoomMediaResponseDTO>> createRoomMedia(@RequestParam("files") List<MultipartFile> files, @RequestParam("roomId") Integer roomId) {
        CreateRoomMediaRequestDTO requestDTO = new CreateRoomMediaRequestDTO(roomId, files, LocalDateTime.now());

        List<RoomMediaResponseDTO> response = roomMediaService.createRoomMedia(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{mediaId}")
    @StoreApi(roles = {"ROLE_ADMIN"})
    public ResponseEntity<RoomMediaResponseDTO> getRoomMediaById(@PathVariable Integer mediaId) {
        RoomMediaResponseDTO response = roomMediaService.getRoomMediaById(mediaId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    @StoreApi(roles = {"ROLE_ADMIN"})
    public ResponseEntity<List<RoomMediaResponseDTO>> getAllRoomMedia() {
        List<RoomMediaResponseDTO> response = roomMediaService.getAllRoomMedia();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/getByRoomId/{roomId}")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<List<RoomMediaResponseDTO>> getByRoomId(@PathVariable Long roomId) {
        List<RoomMediaResponseDTO> response = roomMediaService.getAllRoomByRoomId(roomId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{mediaId}")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<Void> deleteRoomMedia(@PathVariable Integer mediaId) {
        roomMediaService.deleteRoomMedia(mediaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}