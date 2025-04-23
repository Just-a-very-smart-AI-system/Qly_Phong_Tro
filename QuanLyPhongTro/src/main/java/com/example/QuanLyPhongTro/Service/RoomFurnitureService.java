package com.example.QuanLyPhongTro.Service;

import com.example.QuanLyPhongTro.DTO.Request.CreateRoomFurnitureRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.RoomFurnitureResponseDTO;
import com.example.QuanLyPhongTro.Entity.Room;
import com.example.QuanLyPhongTro.Entity.RoomFurniture;
import com.example.QuanLyPhongTro.Mapper.RoomFurnitureMapper;
import com.example.QuanLyPhongTro.Repository.RoomRepository;
import com.example.QuanLyPhongTro.Repository.RoomFurnitureRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomFurnitureService {

    private final RoomFurnitureRepository roomFurnitureRepository;
    private final RoomRepository roomRepository;
    private final RoomFurnitureMapper roomFurnitureMapper;
    private static final Logger logger = LoggerFactory.getLogger(RoomFurnitureService.class);

    @Transactional
    public RoomFurnitureResponseDTO createRoomFurniture(CreateRoomFurnitureRequestDTO requestDTO) {
        validateRequest(requestDTO);

        logger.info("Creating new room furniture for room ID: {}", requestDTO.getRoomId());

        RoomFurniture roomFurniture = roomFurnitureMapper.toEntity(requestDTO);

        // Assign Room
        Room room = roomRepository.findById(requestDTO.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Room not found with ID: " + requestDTO.getRoomId()));
        roomFurniture.setRoom(room);

        roomFurnitureRepository.save(roomFurniture);
        logger.info("Successfully created room furniture for room ID: {}", room.getRoomId());

        // Return DTO with all utilities for the room
        return getRoomFurnitureByRoomId(room.getRoomId());
    }

    public RoomFurnitureResponseDTO getRoomFurnitureById(Integer utilityId) {
        logger.info("Fetching room furniture with ID: {}", utilityId);

        RoomFurniture furniture = roomFurnitureRepository.findById(utilityId)
                .orElseThrow(() -> new IllegalArgumentException("Room furniture not found with ID: " + utilityId));

        return getRoomFurnitureByRoomId(furniture.getRoom().getRoomId());
    }

    public List<RoomFurnitureResponseDTO> getAllRoomFurniture() {
        logger.info("Fetching all room furniture");

        // Group RoomFurniture by roomId and map to DTOs
        return roomRepository.findAll().stream()
                .map(room -> getRoomFurnitureByRoomId(room.getRoomId()))
                .filter(dto -> !dto.getUtility().isEmpty()) // Exclude rooms with no utilities
                .collect(Collectors.toList());
    }

    @Transactional
    public RoomFurnitureResponseDTO updateRoomFurniture(Integer utilityId, CreateRoomFurnitureRequestDTO requestDTO) {
        validateRequest(requestDTO);

        logger.info("Updating room furniture with ID: {}", utilityId);

        RoomFurniture existingFurniture = roomFurnitureRepository.findById(utilityId)
                .orElseThrow(() -> new IllegalArgumentException("Room furniture not found with ID: " + utilityId));

        // Update fields
        roomFurnitureMapper.toEntity(requestDTO, existingFurniture);

        // Update Room if roomId changed
        if (!existingFurniture.getRoom().getRoomId().equals(requestDTO.getRoomId())) {
            Room room = roomRepository.findById(requestDTO.getRoomId())
                    .orElseThrow(() -> new IllegalArgumentException("Room not found with ID: " + requestDTO.getRoomId()));
            existingFurniture.setRoom(room);
        }

        roomFurnitureRepository.save(existingFurniture);
        logger.info("Successfully updated room furniture with ID: {}", utilityId);

        return getRoomFurnitureByRoomId(existingFurniture.getRoom().getRoomId());
    }

    @Transactional
    public RoomFurnitureResponseDTO deleteRoomFurniture(Integer utilityId) {
        logger.info("Deleting room furniture with ID: {}", utilityId);

        RoomFurniture furniture = roomFurnitureRepository.findById(utilityId)
                .orElseThrow(() -> new IllegalArgumentException("Room furniture not found with ID: " + utilityId));

        Integer roomId = furniture.getRoom().getRoomId();
        roomFurnitureRepository.deleteById(utilityId);
        logger.info("Successfully deleted room furniture with ID: {}", utilityId);

        return getRoomFurnitureByRoomId(roomId);
    }

    public RoomFurnitureResponseDTO getRoomFurnitureByRoomId(Integer roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Room not found with ID: " + roomId));

        List<RoomFurniture> furnitureList = roomFurnitureRepository.findAllByRoom(room);
        return roomFurnitureMapper.toDto(furnitureList, room.getRoomId());
    }

    private void validateRequest(CreateRoomFurnitureRequestDTO requestDTO) {
        if (requestDTO.getRoomId() == null) {
            throw new IllegalArgumentException("Room ID cannot be null");
        }
        if (requestDTO.getUtilityName() == null || requestDTO.getUtilityName().trim().isEmpty()) {
            throw new IllegalArgumentException("Utility name cannot be null or empty");
        }
    }
}