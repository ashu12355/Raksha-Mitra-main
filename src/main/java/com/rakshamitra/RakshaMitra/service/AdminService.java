package com.rakshamitra.RakshaMitra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rakshamitra.RakshaMitra.model.Admin;
import com.rakshamitra.RakshaMitra.repository.AdminRepository;

@Service
public class AdminService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email);
        if (admin == null) {
            throw new UsernameNotFoundException("Admin not found: " + email);
        }
        System.out.println("Admin found: " + admin.getEmail());

        return org.springframework.security.core.userdetails.User.builder()
                .username(admin.getEmail())
                .password(admin.getPassword())
                .roles("ADMIN")
                .build();
    }
}
