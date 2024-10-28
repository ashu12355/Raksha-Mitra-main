package com.rakshamitra.RakshaMitra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rakshamitra.RakshaMitra.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
}
