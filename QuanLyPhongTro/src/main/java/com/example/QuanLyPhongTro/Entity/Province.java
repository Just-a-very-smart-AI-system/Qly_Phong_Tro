package com.example.QuanLyPhongTro.Entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "provinces")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Province {

    @Id
    @Column(name = "province_id")
    private Integer provinceId;

    @Column(name = "province_name", length = 100)
    private String provinceName;
}
