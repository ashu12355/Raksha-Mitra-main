package com.rakshamitra.RakshaMitra.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakshamitra.RakshaMitra.model.Volunteer;
import com.rakshamitra.RakshaMitra.repository.VolunteerRepository;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;

     public Volunteer findByEmail(String email) {
        return volunteerRepository.findByEmail(email);
    }

    public Volunteer saveVolunteer(Volunteer volunteer) {
        // Check if the email already exists
        if (volunteerRepository.findByEmail(volunteer.getEmail()) != null) {
            throw new IllegalArgumentException("Email already in use");
        }

        // Ensure the volunteer is not approved by default
        if (!volunteer.isApproved()) {
            volunteer.setApproved(false);
        }
        
        return volunteerRepository.save(volunteer);
    }

    // Get all volunteers pending approval
    public List<Volunteer> getPendingVolunteers() {
        return volunteerRepository.findByIsApprovedFalse();
    }

    // Approve a volunteer by ID
    public void approveVolunteer(Long volunteerId) {
        Volunteer volunteer = volunteerRepository.findById(volunteerId).orElseThrow();
        volunteer.setApproved(true);
        volunteerRepository.save(volunteer);
    }
    
}
