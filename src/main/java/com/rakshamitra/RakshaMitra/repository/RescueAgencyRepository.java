package com.rakshamitra.RakshaMitra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rakshamitra.RakshaMitra.model.RescueAgency;

import java.util.List;

public interface RescueAgencyRepository extends JpaRepository<RescueAgency, Long> {
    RescueAgency findByEmail(String email);
    List<RescueAgency> findByApproved(boolean approved);
}
