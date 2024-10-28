package com.rakshamitra.RakshaMitra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.rakshamitra.RakshaMitra.model.Volunteer;


@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer ,Long> {
    Volunteer findByEmail(String email);
    List<Volunteer> findByIsApprovedFalse();
}
