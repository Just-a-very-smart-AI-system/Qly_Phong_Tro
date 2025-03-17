package com.example.QuanLyPhongTro.Entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "room_utilities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomUtility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer utilityId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "utility_name", length = 50)
    private String utilityName;
}