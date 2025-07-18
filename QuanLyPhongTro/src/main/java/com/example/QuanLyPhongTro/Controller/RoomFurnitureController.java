package com.example.QuanLyPhongTro.Controller;

import com.example.QuanLyPhongTro.Configuration.StoreApi;
import com.example.QuanLyPhongTro.DTO.Request.CreateRoomFurnitureRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomFurnitureResponseDTO;
import com.example.QuanLyPhongTro.Service.RoomFurnitureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room-furniture")
@RequiredArgsConstructor
public class RoomFurnitureController {

    private final RoomFurnitureService roomFurnitureService;

    @PostMapping("/create")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"})
    public ResponseEntity<RoomFurnitureResponseDTO> createRoomUtility(@RequestBody CreateRoomFurnitureRequestDTO requestDTO) {
        RoomFurnitureResponseDTO response = roomFurnitureService.createRoomFurniture(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getByRoomId/{roomId}")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"})
    public ResponseEntity<RoomFurnitureResponseDTO> getRoomUtilityById(@PathVariable Integer roomId) {
        RoomFurnitureResponseDTO response = roomFurnitureService.getRoomFurnitureByRoomId(roomId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    @StoreApi(roles = {"ROLE_ADMIN"})
    public ResponseEntity<List<RoomFurnitureResponseDTO>> getAllRoomUtilities() {
        List<RoomFurnitureResponseDTO> response = roomFurnitureService.getAllRoomFurniture();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{utilityId}")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<Void> deleteRoomUtility(@PathVariable Integer utilityId) {
        roomFurnitureService.deleteRoomFurniture(utilityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}