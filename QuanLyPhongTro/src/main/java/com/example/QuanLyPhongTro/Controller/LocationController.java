package com.example.QuanLyPhongTro.Controller;

import com.example.QuanLyPhongTro.Configuration.StoreApi;
import com.example.QuanLyPhongTro.DTO.Response.CoordinatesDTO;
import com.example.QuanLyPhongTro.Service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sync")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @StoreApi(roles = {"ROLE_ADMIN"})
    @PostMapping("/locations")
    public ResponseEntity<String> syncLocations() {
        locationService.syncLocationsFromApi();
        return ResponseEntity.ok("Locations synced successfully");
    }
    @StoreApi(roles = {"ROLE_ADMIN"})
    @GetMapping("/coordinates")
    public ResponseEntity<CoordinatesDTO> getCoordinatesFromAddress(@RequestParam String address) {
        CoordinatesDTO coordinates = locationService.getCoordinatesFromAddress(address);
        return ResponseEntity.ok(coordinates);
    }
}