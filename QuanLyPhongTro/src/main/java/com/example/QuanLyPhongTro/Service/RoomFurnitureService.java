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
        logger.info("Creating new room furniture for room ID: {}", requestDTO.getRoomId());

        Room room = roomRepository.findById(requestDTO.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Room not found with ID: " + requestDTO.getRoomId()));

        // Create a RoomFurniture entity for each furniture name
        List<RoomFurniture> furnitureList = requestDTO.getFurnitureName().stream()
                .map(name -> {
                    RoomFurniture furniture = new RoomFurniture();
                    furniture.setUtilityName(name);
                    furniture.setRoom(room);
                    return furniture;
                })
                .collect(Collectors.toList());

        roomFurnitureRepository.saveAll(furnitureList);
        logger.info("Successfully created {} furniture items for room ID: {}", furnitureList.size(), room.getRoomId());

        // Return DTO with all furniture for the room
        return getRoomFurnitureByRoomId(room.getRoomId());
    }

    public RoomFurnitureResponseDTO getRoomFurnitureById(Integer furnitureId) {
        logger.info("Fetching room furniture with ID: {}", furnitureId);

        RoomFurniture furniture = roomFurnitureRepository.findById(furnitureId)
                .orElseThrow(() -> new IllegalArgumentException("Room furniture not found with ID: " + furnitureId));

        return getRoomFurnitureByRoomId(furniture.getRoom().getRoomId());
    }

    public List<RoomFurnitureResponseDTO> getAllRoomFurniture() {
        logger.info("Fetching all room furniture");

        // Group RoomFurniture by roomId and map to DTOs
        return roomRepository.findAll().stream()
                .map(room -> getRoomFurnitureByRoomId(room.getRoomId()))
                .filter(dto -> !dto.getUtility().isEmpty()) // Exclude rooms with no furniture
                .collect(Collectors.toList());
    }

    @Transactional
    public RoomFurnitureResponseDTO updateRoomFurniture(Integer furnitureId, CreateRoomFurnitureRequestDTO requestDTO) {
        validateRequest(requestDTO);

        logger.info("Updating room furniture with ID: {}", furnitureId);

        RoomFurniture existingFurniture = roomFurnitureRepository.findById(furnitureId)
                .orElseThrow(() -> new IllegalArgumentException("Room furniture not found with ID: " + furnitureId));

        // Since requestDTO contains a list, we'll update the single furniture item with the first name
        // This assumes the update is for a single furniture item
        if (!requestDTO.getFurnitureName().isEmpty()) {
            existingFurniture.setUtilityName(requestDTO.getFurnitureName().get(0));
        }

        // Update Room if roomId changed
        if (!existingFurniture.getRoom().getRoomId().equals(requestDTO.getRoomId())) {
            Room room = roomRepository.findById(requestDTO.getRoomId())
                    .orElseThrow(() -> new IllegalArgumentException("Room not found with ID: " + requestDTO.getRoomId()));
            existingFurniture.setRoom(room);
        }

        roomFurnitureRepository.save(existingFurniture);
        logger.info("Successfully updated room furniture with ID: {}", furnitureId);

        return getRoomFurnitureByRoomId(existingFurniture.getRoom().getRoomId());
    }

    @Transactional
    public RoomFurnitureResponseDTO deleteRoomFurniture(Integer furnitureId) {
        logger.info("Deleting room furniture with ID: {}", furnitureId);

        RoomFurniture furniture = roomFurnitureRepository.findById(furnitureId)
                .orElseThrow(() -> new IllegalArgumentException("Room furniture not found with ID: " + furnitureId));

        Integer roomId = furniture.getRoom().getRoomId();
        roomFurnitureRepository.deleteById(furnitureId);
        logger.info("Successfully deleted room furniture with ID: {}", furnitureId);

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
        if (requestDTO.getFurnitureName() == null || requestDTO.getFurnitureName().isEmpty()) {
            throw new IllegalArgumentException("Furniture name list cannot be null or empty");
        }
        for (String name : requestDTO.getFurnitureName()) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Furniture name cannot be null or empty");
            }
        }
    }
}