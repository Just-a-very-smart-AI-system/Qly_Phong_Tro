package com.example.QuanLyPhongTro.Service;

import com.example.QuanLyPhongTro.Entity.Manager;
import com.example.QuanLyPhongTro.Entity.User;
import com.example.QuanLyPhongTro.Repository.ManagerRepository;
import com.example.QuanLyPhongTro.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserAuthenService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try to find in User repository
        User user = userRepository.findByUserName(username).orElse(null);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
            );
        }

        // Try to find in Manager repository
        Manager manager = managerRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new org.springframework.security.core.userdetails.User(
                manager.getUserName(),
                manager.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(manager.getRole()))
        );
    }
}