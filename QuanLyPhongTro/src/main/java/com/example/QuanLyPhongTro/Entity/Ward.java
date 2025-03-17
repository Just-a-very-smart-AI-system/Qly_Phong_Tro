package com.example.QuanLyPhongTro.Entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "wards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wardId;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    @Column(name = "ward_name", length = 100)
    private String wardName;
}
