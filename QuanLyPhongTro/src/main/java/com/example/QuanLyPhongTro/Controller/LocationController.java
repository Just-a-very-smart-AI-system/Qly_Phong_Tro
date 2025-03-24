package com.example.QuanLyPhongTro.Controller;

import com.example.QuanLyPhongTro.Service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sync")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @PostMapping("/locations")
    public ResponseEntity<String> syncLocations() {
        locationService.syncLocationsFromApi();
        return ResponseEntity.ok("Locations synced successfully");
    }
}