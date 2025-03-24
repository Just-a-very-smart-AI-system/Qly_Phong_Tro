package com.example.QuanLyPhongTro.DTO.Response;


import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ProvinceApiResponse {
    private String name;
    private Integer code;
    private String codename;
    private String division_type;
    private Integer phone_code;
    private List<DistrictApiResponse> districts;

    @Data
    public static class DistrictApiResponse {
        private String name;
        private Integer code;
        private String codename;
        private String division_type;
        private String short_codename;
        private List<WardApiResponse> wards;
    }

    @Data
    public static class WardApiResponse {
        private String name;
        private Integer code;
        private String codename;
        private String division_type;
        private String short_codename;
    }
}
