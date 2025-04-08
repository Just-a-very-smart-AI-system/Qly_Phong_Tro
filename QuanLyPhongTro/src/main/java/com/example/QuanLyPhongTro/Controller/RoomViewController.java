package com.example.QuanLyPhongTro.Controller;

import com.example.QuanLyPhongTro.DTO.Request.CreateRoomViewRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomViewResponseDTO;
import com.example.QuanLyPhongTro.Service.RoomViewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room-views")
@RequiredArgsConstructor
public class RoomViewController {

    private final RoomViewService roomViewService;

    @PostMapping("/create")
    public ResponseEntity<RoomViewResponseDTO> createRoomView(@Valid @RequestBody CreateRoomViewRequestDTO requestDTO) {
        RoomViewResponseDTO response = roomViewService.createRoomView(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{viewId}")
    public ResponseEntity<RoomViewResponseDTO> getRoomViewById(@PathVariable Integer viewId) {
        RoomViewResponseDTO response = roomViewService.getRoomViewById(viewId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RoomViewResponseDTO>> getAllRoomViews() {
        List<RoomViewResponseDTO> response = roomViewService.getAllRoomViews();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{viewId}")
    public ResponseEntity<Void> deleteRoomView(@PathVariable Integer viewId) {
        roomViewService.deleteRoomView(viewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}