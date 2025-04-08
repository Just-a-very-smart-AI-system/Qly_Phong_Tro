package com.example.QuanLyPhongTro.Service;


import com.example.QuanLyPhongTro.DTO.Request.CreateDistrictRequestDTO;
import com.example.QuanLyPhongTro.DTO.Request.CreateProvinceRequestDTO;
import com.example.QuanLyPhongTro.DTO.Request.CreateWardRequestDTO;
import com.example.QuanLyPhongTro.DTO.Response.CoordinatesDTO;
import com.example.QuanLyPhongTro.DTO.Response.ProvinceApiResponse;
import com.example.QuanLyPhongTro.Entity.District;
import com.example.QuanLyPhongTro.Entity.Province;
import com.example.QuanLyPhongTro.Entity.Ward;
import com.example.QuanLyPhongTro.Repository.DistrictRepository;
import com.example.QuanLyPhongTro.Repository.ProvinceRepository;
import com.example.QuanLyPhongTro.Repository.WardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final WardRepository wardRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Transactional
    public void syncLocationsFromApi() {
        // Gọi API để lấy danh sách tỉnh/thành phố
        String url = "https://provinces.open-api.vn/api/?depth=3";
        List<ProvinceApiResponse> provinces = restTemplate.getForObject(url, List.class);

        if (provinces == null) {
            throw new RuntimeException("Failed to fetch data from API");
        }

        // Lưu từng tỉnh, quận/huyện, xã/phường
        for (Object provinceObj : provinces) {
            ProvinceApiResponse provinceData = mapToProvinceApiResponse(provinceObj);
            saveProvince(provinceData);
        }
    }

    // Lưu Province
    private void saveProvince(ProvinceApiResponse provinceData) {
        CreateProvinceRequestDTO CreateProvinceRequestDTO = new CreateProvinceRequestDTO();
        CreateProvinceRequestDTO.setProvinceId(provinceData.getCode());
        CreateProvinceRequestDTO.setProvinceName(provinceData.getName());

        // Kiểm tra xem Province đã tồn tại chưa
        if (provinceRepository.existsById(CreateProvinceRequestDTO.getProvinceId())) {
            System.out.println("Province with ID " + CreateProvinceRequestDTO.getProvinceId() + " already exists, skipping...");
            return;
        }

        Province province = new Province();
        province.setProvinceId(CreateProvinceRequestDTO.getProvinceId());
        province.setProvinceName(CreateProvinceRequestDTO.getProvinceName());
        provinceRepository.save(province);
        System.out.println("Saved province: " + CreateProvinceRequestDTO.getProvinceName());

        // Lưu các District thuộc Province này
        if (provinceData.getDistricts() != null) {
            for (ProvinceApiResponse.DistrictApiResponse districtData : provinceData.getDistricts()) {
                saveDistrict(districtData, province);
            }
        }
    }

    // Lưu District
    private void saveDistrict(ProvinceApiResponse.DistrictApiResponse districtData, Province province) {
        CreateDistrictRequestDTO CreateDistrictRequestDTO = new CreateDistrictRequestDTO();
        CreateDistrictRequestDTO.setDistrictId(districtData.getCode());
        CreateDistrictRequestDTO.setProvinceId(province.getProvinceId());
        CreateDistrictRequestDTO.setDistrictName(districtData.getName());

        // Kiểm tra xem District đã tồn tại chưa
        if (districtRepository.existsById(CreateDistrictRequestDTO.getDistrictId())) {
            System.out.println("District with ID " + CreateDistrictRequestDTO.getDistrictId() + " already exists, skipping...");
            return;
        }

        District district = new District();
        district.setDistrictId(CreateDistrictRequestDTO.getDistrictId());
        district.setDistrictName(CreateDistrictRequestDTO.getDistrictName());
        district.setProvince(province);
        districtRepository.save(district);
        System.out.println("Saved district: " + CreateDistrictRequestDTO.getDistrictName());

        // Lưu các Ward thuộc District này
        if (districtData.getWards() != null) {
            for (ProvinceApiResponse.WardApiResponse wardData : districtData.getWards()) {
                saveWard(wardData, district);
            }
        }
    }

    // Lưu Ward
    private void saveWard(ProvinceApiResponse.WardApiResponse wardData, District district) {
        CreateWardRequestDTO CreateWardRequestDTO = new CreateWardRequestDTO();
        CreateWardRequestDTO.setWardId(wardData.getCode());
        CreateWardRequestDTO.setDistrictId(district.getDistrictId());
        CreateWardRequestDTO.setWardName(wardData.getName());

        // Kiểm tra xem Ward đã tồn tại chưa
        if (wardRepository.existsById(CreateWardRequestDTO.getWardId())) {
            System.out.println("Ward with ID " + CreateWardRequestDTO.getWardId() + " already exists, skipping...");
            return;
        }

        Ward ward = new Ward();
        ward.setWardId(CreateWardRequestDTO.getWardId());
        ward.setWardName(CreateWardRequestDTO.getWardName());
        ward.setDistrict(district);
        wardRepository.save(ward);
        System.out.println("Saved ward: " + CreateWardRequestDTO.getWardName());
    }

    // Hàm ánh xạ dữ liệu từ API (do API trả về List<Object>, cần ánh xạ thủ công)
    @SuppressWarnings("unchecked")
    private ProvinceApiResponse mapToProvinceApiResponse(Object provinceObj) {
        ProvinceApiResponse province = new ProvinceApiResponse();
        if (provinceObj instanceof java.util.Map) {
            java.util.Map<String, Object> provinceMap = (java.util.Map<String, Object>) provinceObj;
            province.setName((String) provinceMap.get("name"));
            province.setCode((Integer) provinceMap.get("code"));
            province.setCodename((String) provinceMap.get("codename"));
            province.setDivision_type((String) provinceMap.get("division_type"));
            province.setPhone_code((Integer) provinceMap.get("phone_code"));

            List<ProvinceApiResponse.DistrictApiResponse> districts = new java.util.ArrayList<>();
            List<Object> districtList = (List<Object>) provinceMap.get("districts");
            if (districtList != null) {
                for (Object districtObj : districtList) {
                    ProvinceApiResponse.DistrictApiResponse district = new ProvinceApiResponse.DistrictApiResponse();
                    java.util.Map<String, Object> districtMap = (java.util.Map<String, Object>) districtObj;
                    district.setName((String) districtMap.get("name"));
                    district.setCode((Integer) districtMap.get("code"));
                    district.setCodename((String) districtMap.get("codename"));
                    district.setDivision_type((String) districtMap.get("division_type"));
                    district.setShort_codename((String) districtMap.get("short_codename"));

                    List<ProvinceApiResponse.WardApiResponse> wards = new java.util.ArrayList<>();
                    List<Object> wardList = (List<Object>) districtMap.get("wards");
                    if (wardList != null) {
                        for (Object wardObj : wardList) {
                            ProvinceApiResponse.WardApiResponse ward = new ProvinceApiResponse.WardApiResponse();
                            java.util.Map<String, Object> wardMap = (java.util.Map<String, Object>) wardObj;
                            ward.setName((String) wardMap.get("name"));
                            ward.setCode((Integer) wardMap.get("code"));
                            ward.setCodename((String) wardMap.get("codename"));
                            ward.setDivision_type((String) wardMap.get("division_type"));
                            ward.setShort_codename((String) wardMap.get("short_codename"));
                            wards.add(ward);
                        }
                    }
                    district.setWards(wards);
                    districts.add(district);
                }
            }
            province.setDistricts(districts);
        }
        return province;
    }

    // Hàm lấy tọa độ từ địa chỉ
    public CoordinatesDTO getCoordinatesFromAddress(String address) {
        // Kiểm tra đầu vào
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }

        String url = "https://nominatim.openstreetmap.org/search?q=" + address + "&format=json&addressdetails=1&countrycodes=VN";

        // Thêm header User-Agent
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "QuanLyPhongTro/1.0 (contact: your-email@example.com)");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Khởi tạo RestTemplate (giả định được inject hoặc khởi tạo trước)
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Gọi API Nominatim
            ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, entity, List.class);

            // Kiểm tra phản hồi
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null && !response.getBody().isEmpty()) {
                // Lấy tọa độ từ kết quả đầu tiên
                Map<String, Object> result = (Map<String, Object>) response.getBody().get(0);
                String lat = result.get("lat").toString();
                String lon = result.get("lon").toString();

                // Chuyển đổi sang BigDecimal và kiểm tra giá trị hợp lệ
                CoordinatesDTO coordinates = new CoordinatesDTO();
                coordinates.setLatitude(lat != null ? new BigDecimal(lat) : null);
                coordinates.setLongitude(lon != null ? new BigDecimal(lon) : null);
                return coordinates;
            } else {
                // Không tìm thấy tọa độ, trả về DTO với giá trị null
                CoordinatesDTO coordinates = new CoordinatesDTO();
                coordinates.setLatitude(null);
                coordinates.setLongitude(null);
                return coordinates;
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ (ví dụ: lỗi mạng, định dạng JSON không hợp lệ)
            CoordinatesDTO coordinates = new CoordinatesDTO();
            coordinates.setLatitude(null);
            coordinates.setLongitude(null);
            return coordinates;
        }
    }
}