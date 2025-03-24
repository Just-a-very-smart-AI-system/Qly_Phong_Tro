package com.example.QuanLyPhongTro.Controller;

import com.example.QuanLyPhongTro.DTO.Request.CreateRoomRequestDTO;
import com.example.QuanLyPhongTro.DTO.Request.UpdateRoomRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomResponseDTO;
import com.example.QuanLyPhongTro.Service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/create")
    public ResponseEntity<RoomResponseDTO> createRoom(@Valid @RequestBody CreateRoomRequestDTO requestDTO) {
        RoomResponseDTO response = roomService.createRoom(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{roomId}")
    public ResponseEntity<RoomResponseDTO> updateRoom(@PathVariable Integer roomId, @Valid @RequestBody UpdateRoomRequestDTO requestDTO) {
        RoomResponseDTO response = roomService.updateRoom(roomId, requestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getById/{roomId}")
    public ResponseEntity<RoomResponseDTO> getRoomById(@PathVariable Integer roomId) {
        RoomResponseDTO response = roomService.getRoomById(roomId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RoomResponseDTO>> getAllRooms() {
        List<RoomResponseDTO> response = roomService.getAllRooms();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Integer roomId) {
        roomService.deleteRoom(roomId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
