package com.example.QuanLyPhongTro.Controller;

import com.example.QuanLyPhongTro.Configuration.StoreApi;
import com.example.QuanLyPhongTro.DTO.Request.CreateUserRequestDTO;
import com.example.QuanLyPhongTro.DTO.Request.UpdateUserRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.UserResponseDTO;
import com.example.QuanLyPhongTro.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody CreateUserRequestDTO requestDTO) {
        UserResponseDTO response = userService.createUser(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_USER"})
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Integer userId, @Valid @RequestBody UpdateUserRequestDTO requestDTO) {
        UserResponseDTO response = userService.updateUser(userId, requestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getById/{userId}")
    @StoreApi(roles = {"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Integer userId) {
        UserResponseDTO response = userService.getUserById(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @StoreApi(roles = {"ROLE_ADMIN"})
    @GetMapping("/getAll")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> response = userService.getAllUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @StoreApi(roles = {"ROLE_ADMIN"})
    @DeleteMapping("delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
