package com.example.QuanLyPhongTro.Entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "districts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer districtId;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    @Column(name = "district_name", length = 100)
    private String districtName;
}