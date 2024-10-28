package com.rakshamitra.RakshaMitra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute; // Import this
import org.springframework.web.bind.annotation.RequestParam;

import com.rakshamitra.RakshaMitra.model.Volunteer;
import com.rakshamitra.RakshaMitra.service.VolunteerService;

@Controller
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    // Volunteer Login Page
    @GetMapping("/student/login")
    public String showVolunteerLoginPage() {
        return "volunteer_login";
    }

    // Login Submission
    @PostMapping("/student/login")
    public String volunteerLogin(
            @RequestParam String email,
            @RequestParam String password,
            Model model) {
        Volunteer volunteer = volunteerService.findByEmail(email);
        if (volunteer != null && volunteer.getPassword().equals(password)) {
            if (volunteer.isApproved()) {
                return "volunteer_dashboard"; // Dashboard after successful login
            } else {
                model.addAttribute("error", "Account not approved by Admin.");
                return "volunteer_login";
            }
        }
        model.addAttribute("error", "Invalid credentials.");
        return "volunteer_login";
    }

    // Registration Page
    @GetMapping("/student/register")
    public String showVolunteerRegistrationPage() {
        return "volunteer_registration"; // Ensure this template exists
    }

    // Registration Submission
    @PostMapping("/student/register")
    public String registerVolunteer(@ModelAttribute Volunteer volunteer, Model model) {
        try {
            // Check if email is already registered using the service
            if (volunteerService.findByEmail(volunteer.getEmail()) != null) {
                throw new IllegalArgumentException("Email already in use");
            }

            // Set approval status to false initially
            volunteer.setApproved(false); 
            // Save the volunteer using the service
            volunteerService.saveVolunteer(volunteer);
            return "redirect:/student/login"; // Redirect to login page after successful registration
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "volunteer_registration"; // Return to registration page with error message
        }
    }

    @GetMapping("/admin/students/pending")
    public String showPendingVolunteers(Model model) {
        model.addAttribute("pendingVolunteers", volunteerService.getPendingVolunteers());
        return "admin_pending_volunteers";
    }

    @PostMapping("/admin/student/approve/{id}")
    public String approveVolunteer(@PathVariable Long id) {
        volunteerService.approveVolunteer(id);
        return "redirect:/admin/students/pending"; // Redirect back to pending volunteers
    }
}
