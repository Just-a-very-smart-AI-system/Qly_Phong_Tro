package com.example.QuanLyPhongTro.Configuration;

import com.example.QuanLyPhongTro.Entity.Manager;
import com.example.QuanLyPhongTro.Entity.User;
import com.example.QuanLyPhongTro.Repository.ManagerRepository;
import com.example.QuanLyPhongTro.Repository.UserRepository;
import com.example.QuanLyPhongTro.Service.ManagerService;
import com.example.QuanLyPhongTro.Service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    @Autowired
    private UserRepository userService;
    @Autowired
    private ManagerRepository managerService;
    private final String secret = "EVana8qKD1pjK6Bba2ti4dSe1oIeSUPl";
    private final long expiration = 24 * 60 * 60 * 1000; // 1 ngày (ms)

    // Tạo JWT với username và roles
    public String generateToken(String username, Collection<? extends GrantedAuthority> authorities) {
        User user = userService.findByUserName(username).orElse(null);
        Manager manager = managerService.findByUserName(username).orElse(null);
        Integer id;
        if(user != null){
            id = user.getUserId();
        }
        else{
            id = manager.getManagerId();
        }
        Claims claims = Jwts.claims()
                .setSubject(username)
                        .setId(id.toString());

        claims.put("roles", authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // Lấy username từ JWT
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Lấy roles từ JWT
    public Collection<String> getRolesFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("roles", Collection.class);
    }

    // Kiểm tra token hợp lệ
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}