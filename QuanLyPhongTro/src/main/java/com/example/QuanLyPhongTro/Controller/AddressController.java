package com.example.QuanLyPhongTro.Controller;

import com.example.QuanLyPhongTro.Configuration.StoreApi;
import com.example.QuanLyPhongTro.DTO.Request.CreateAddressRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.AddressResponseDTO;
import com.example.QuanLyPhongTro.DTO.Response.CoordinatesDTO;
import com.example.QuanLyPhongTro.Service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/create")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<AddressResponseDTO> createAddress(@Valid @RequestBody CreateAddressRequestDTO requestDTO) {
        AddressResponseDTO response = addressService.createAddress(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    @PutMapping("/update/{addressId}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable Integer addressId, @Valid @RequestBody CreateAddressRequestDTO requestDTO) {
        AddressResponseDTO response = addressService.updateAddress(addressId, requestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @StoreApi(roles = {"ROLE_ADMIN"})
    @GetMapping("/getById/{addressId}")
    public ResponseEntity<AddressResponseDTO> getAddressById(@PathVariable Integer addressId) {
        AddressResponseDTO response = addressService.getAddressById(addressId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @StoreApi(roles = {"ROLE_ADMIN"})
    @GetMapping("/getAll")
    public ResponseEntity<List<AddressResponseDTO>> getAllAddresses() {
        List<AddressResponseDTO> response = addressService.getAllAddresses();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // Tìm kiếm Address theo provinceName
    @StoreApi(roles = {"ROLE_ADMIN"})
    @GetMapping("/search/by-province-name")
    public ResponseEntity<List<AddressResponseDTO>> findByProvinceName(@RequestParam String provinceName) {
        List<AddressResponseDTO> addresses = addressService.findByProvinceName(provinceName);
        return ResponseEntity.ok(addresses);
    }

    // Tìm kiếm Address theo districtName
    @StoreApi(roles = {"ROLE_ADMIN"})
    @GetMapping("/search/by-district-name")
    public ResponseEntity<List<AddressResponseDTO>> findByDistrictName(@RequestParam String districtName) {
        List<AddressResponseDTO> addresses = addressService.findByDistrictName(districtName);
        return ResponseEntity.ok(addresses);
    }

    // Tìm kiếm Address theo districtId
    @StoreApi(roles = {"ROLE_ADMIN"})
    @GetMapping("/search/by-district-id")
    public ResponseEntity<List<AddressResponseDTO>> findByDistrictId(@RequestParam Integer districtId) {
        List<AddressResponseDTO> addresses = addressService.findByDistrictId(districtId);
        return ResponseEntity.ok(addresses);
    }

    // Tìm kiếm Address theo provinceId
    @StoreApi(roles = {"ROLE_ADMIN"})
    @GetMapping("/search/by-province-id")
    public ResponseEntity<List<AddressResponseDTO>> findByProvinceId(@RequestParam Integer provinceId) {
        List<AddressResponseDTO> addresses = addressService.findByProvinceId(provinceId);
        return ResponseEntity.ok(addresses);
    }
    @StoreApi(roles = {"ROLE_ADMIN"})
    @DeleteMapping("/delete/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Integer addressId) {
        addressService.deleteAddress(addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @StoreApi(roles = {"ROLE_ADMIN"})
    @GetMapping("/getDiractionUrl")
    public ResponseEntity<String> getDiractionUrl(@RequestBody CoordinatesDTO coordinatesDTO, @RequestParam String travleMode){
        return ResponseEntity.ok( addressService.getDirectionsUrl(coordinatesDTO, travleMode));
    }
}