package com.example.QuanLyPhongTro.Entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "room_media")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mediaId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Enumerated(EnumType.STRING)
    @Column(name = "media_type")
    private MediaType mediaType;

    @Column(name = "media_url", length = 255)
    private String mediaUrl;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;

    public enum MediaType {
        IMAGE, VIDEO // Điều chỉnh theo giá trị thực tế của ENUM trong DB
    }
}