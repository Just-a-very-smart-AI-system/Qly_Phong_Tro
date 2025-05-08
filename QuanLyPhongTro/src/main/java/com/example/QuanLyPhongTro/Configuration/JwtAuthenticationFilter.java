package com.example.QuanLyPhongTro.Configuration;

import com.example.QuanLyPhongTro.Entity.ApiUrl;
import com.example.QuanLyPhongTro.Repository.ApiUrlRepository;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ApiUrlRepository apiUrlRepository;

    private Map<String, List<ApiUrl>> apiUrlCache = new HashMap<>();
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @PostConstruct
    public void initCache() {
        logger.info("Initializing ApiUrl cache...");
        List<ApiUrl> apiUrls = apiUrlRepository.findAll();
        apiUrlCache.clear();
        apiUrls.forEach(apiUrl -> {
            apiUrlCache.computeIfAbsent(apiUrl.getUrl(), k -> new java.util.ArrayList<>()).add(apiUrl);
            logger.debug("Cached ApiUrl: {} with role {}", apiUrl.getUrl(), apiUrl.getRole().name());
        });
        logger.info("ApiUrl cache initialized with {} entries", apiUrlCache.size());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        // Trích xuất JWT từ header
        if (header != null && header.startsWith("Bearer ")) {
            jwt = header.substring(7);
            try {
                username = jwtUtil.getUsernameFromToken(jwt);
            } catch (Exception e) {
                logger.error("Error extracting username from JWT: {}", e.getMessage());
            }
        } else {
            logger.debug("No Bearer token found in Authorization header");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtUtil.validateToken(jwt)) {
                // Lấy danh sách roles từ token
                List<String> roles = (List<String>) jwtUtil.getRolesFromToken(jwt);
                logger.debug("Roles from JWT for user {}: {}", username, roles);

                // Lấy URL từ yêu cầu, loại bỏ query parameters
                String requestUrl = request.getRequestURI().split("\\?")[0];
                logger.debug("Request URL: {}", requestUrl);

                // Tìm danh sách ApiUrl khớp với URL yêu cầu
                List<ApiUrl> matchedApiUrls = apiUrlCache.entrySet().stream()
                        .filter(entry -> pathMatcher.match(entry.getKey(), requestUrl))
                        .flatMap(entry -> entry.getValue().stream())
                        .collect(Collectors.toList());

                logger.debug("Matched ApiUrls for {}: {}", requestUrl,
                        matchedApiUrls.stream().map(apiUrl -> apiUrl.getUrl() + ":" + apiUrl.getRole().name()).collect(Collectors.toList()));

                // Kiểm tra xem có role nào từ JWT khớp với role trong ApiUrl không
                boolean isAuthorized = matchedApiUrls.stream()
                        .anyMatch(apiUrl -> roles.contains(apiUrl.getRole().name()));

                if (!isAuthorized) {
                    logger.warn("Access denied for user {} on URL {} with roles {}. Matched ApiUrls: {}",
                            username, requestUrl, roles,
                            matchedApiUrls.stream().map(apiUrl -> apiUrl.getUrl() + ":" + apiUrl.getRole().name()).collect(Collectors.toList()));
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write("Access Denied: Insufficient permissions for this resource.");
                    return;
                }

                // Nếu phân quyền thành công, thiết lập Authentication
                List<SimpleGrantedAuthority> authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, authorities);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.debug("Authentication set for user {}", username);
            } else {
                logger.warn("Invalid JWT token for user {}", username);
            }
        }
        chain.doFilter(request, response);
    }
}