package com.example.QuanLyPhongTro.Controller;


import com.example.QuanLyPhongTro.Configuration.StoreApi;
import com.example.QuanLyPhongTro.DTO.Request.CreateFavoriteRoomRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.FavoriteRoomResponseDTO;
import com.example.QuanLyPhongTro.Service.FavoriteRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite-rooms")
@RequiredArgsConstructor
public class FavoriteRoomController {

    private final FavoriteRoomService favoriteRoomService;

    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER"})
    @PostMapping("/create")
    public ResponseEntity<FavoriteRoomResponseDTO> createFavoriteRoom(@Valid @RequestBody CreateFavoriteRoomRequestDTO requestDTO) {
        FavoriteRoomResponseDTO response = favoriteRoomService.createFavoriteRoom(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @StoreApi(roles = {"ROLE_ADMIN"})
    @GetMapping("/getById/{favoriteId}")
    public ResponseEntity<FavoriteRoomResponseDTO> getFavoriteRoomById(@PathVariable Integer favoriteId) {
        FavoriteRoomResponseDTO response = favoriteRoomService.getFavoriteRoomById(favoriteId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @StoreApi(roles = {"ROLE_ADMIN"})
    @GetMapping("/getAll")
    public ResponseEntity<List<FavoriteRoomResponseDTO>> getAllFavoriteRooms() {
        List<FavoriteRoomResponseDTO> response = favoriteRoomService.getAllFavoriteRooms();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @StoreApi(roles = {"ROLE_ADMIN"})
    @DeleteMapping("/delete/{favoriteId}")
    public ResponseEntity<Void> deleteFavoriteRoom(@PathVariable Integer favoriteId) {
        favoriteRoomService.deleteFavoriteRoom(favoriteId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
