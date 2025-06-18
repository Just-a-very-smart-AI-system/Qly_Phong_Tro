package com.example.QuanLyPhongTro.Controller;

import com.example.QuanLyPhongTro.Configuration.StoreApi;
import com.example.QuanLyPhongTro.DTO.Request.CreateRoomRequestDTO;
import com.example.QuanLyPhongTro.DTO.Request.UpdateRoomRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomResponseDTO;
import com.example.QuanLyPhongTro.Service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    @PostMapping("/create")
    public ResponseEntity<RoomResponseDTO> createRoom(@Valid @RequestBody CreateRoomRequestDTO requestDTO) {
        RoomResponseDTO response = roomService.createRoom(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{roomId}")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<RoomResponseDTO> updateRoom(@PathVariable Integer roomId, @Valid @RequestBody UpdateRoomRequestDTO requestDTO) {
        RoomResponseDTO response = roomService.updateRoom(roomId, requestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getById/{roomId}")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<RoomResponseDTO> getRoomById(@PathVariable Integer roomId) {
        RoomResponseDTO response = roomService.getRoomById(roomId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<List<RoomResponseDTO>> getAllRooms() {
        List<RoomResponseDTO> response = roomService.getAllRooms();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{roomId}")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<Void> deleteRoom(@PathVariable Integer roomId) {
        roomService.deleteRoom(roomId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/by-province-name")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<List<RoomResponseDTO>> findByProvinceName(@RequestParam String provinceName) {
        List<RoomResponseDTO> rooms = roomService.findByProvinceName(provinceName);
        return ResponseEntity.ok(rooms);
    }
    @GetMapping("/search/by-ward-name")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<List<RoomResponseDTO>> findByWardName(@RequestParam String wardName) {
        List<RoomResponseDTO> rooms = roomService.findByWardName(wardName);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/search/by-ward-id")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<List<RoomResponseDTO>> findByWardId(@RequestParam Integer wardId) {
        List<RoomResponseDTO> rooms = roomService.findByWardId(wardId);
        return ResponseEntity.ok(rooms);
    }
    @GetMapping("/search/by-district-name")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<List<RoomResponseDTO>> findByDistrictName(@RequestParam String districtName) {
        List<RoomResponseDTO> rooms = roomService.findByDistrictName(districtName);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/search/by-district-id")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<List<RoomResponseDTO>> findByDistrictId(@RequestParam Integer districtId) {
        List<RoomResponseDTO> rooms = roomService.findByDistrictId(districtId);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/search/by-province-id")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
        public ResponseEntity<List<RoomResponseDTO>> findByProvinceId(@RequestParam Integer provinceId) {
        List<RoomResponseDTO> rooms = roomService.findByProvinceId(provinceId);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/search/by-availability")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<List<RoomResponseDTO>> findByIsAvailable(@RequestParam Integer isAvailable) {
        List<RoomResponseDTO> rooms = roomService.findByIsAvailable(isAvailable);
        return ResponseEntity.ok(rooms);
    }
    @GetMapping("/search/by-price-range")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<List<RoomResponseDTO>> findByPriceBetween(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        List<RoomResponseDTO> rooms = roomService.findByPriceBetween(minPrice, maxPrice);
        return ResponseEntity.ok(rooms);
    }
    @GetMapping("/search/by-manager-id")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<List<RoomResponseDTO>> FindByManagerId(@RequestParam Integer id) {
        List<RoomResponseDTO> rooms = roomService.findByManagerId(id);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/getDiractionUrl")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<String> GetDiractionUrl(@RequestParam Integer roomId){
        String diractionUrl = roomService.getDiractionUrl(roomId);

        return ResponseEntity.ok(diractionUrl);
    }
    @GetMapping("/search")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<Page<RoomResponseDTO>> searchRooms(
            @RequestParam(value = "provinceId", required = false) Integer provinceId,
            @RequestParam(value = "districtId", required = false) Integer districtId,
            @RequestParam(value = "wardId", required = false) Integer wardId,
            @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,
            @RequestParam(value = "page", defaultValue = "1") int page
    ) {
        Page<RoomResponseDTO> rooms = roomService.searchRooms(provinceId, districtId, wardId, minPrice, maxPrice, page);
        return ResponseEntity.ok(rooms);
    }
}