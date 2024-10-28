package com.rakshamitra.RakshaMitra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rakshamitra.RakshaMitra.forms.RescueAgencyForm;
import com.rakshamitra.RakshaMitra.helpers.Message;
import com.rakshamitra.RakshaMitra.helpers.MessageType;
import com.rakshamitra.RakshaMitra.model.RescueAgency;
import com.rakshamitra.RakshaMitra.service.RescueAgencyService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class RescueAgencyController {

    @Autowired
    private RescueAgencyService rescueAgencyService;

    @GetMapping("/college/login")
    public String showAgencyLoginPage(Model model, HttpSession session) {
        // Check for success message
        Message message = (Message) session.getAttribute("message");
        if (message != null) {
            model.addAttribute("message", message);
            session.removeAttribute("message");
        }
        return "rescue_agency_login";
    }
    

    @PostMapping("/college/login")
    public String agencyLogin(String email, String password, Model model) {
        RescueAgency agency = rescueAgencyService.findByEmail(email);
        if (agency != null && agency.isApproved() && agency.getPassword().equals(password)) {
            return "agency_dashboard";
        }
        model.addAttribute("error", "Invalid credentials or agency not approved.");
        return "rescue_agency_login";
    }

    @GetMapping("/college/register")
    public String showAgencyRegistrationPage(Model model) {
        RescueAgencyForm rescueAgencyForm = new RescueAgencyForm();
        model.addAttribute("rescueAgencyForm", rescueAgencyForm);
        return "rescue_agency_reg";
    }

    @RequestMapping(value = "/college/register", method = RequestMethod.POST)
    public String registerAgency(@Valid @ModelAttribute RescueAgencyForm rescueAgencyForm, 
                                  BindingResult bindingResult, HttpSession session) {
        // Validate the Form
        if (bindingResult.hasErrors()) {
            return "rescue_agency_reg";
        }

        // Saving to database
        RescueAgency rescueAgency = new RescueAgency();
        rescueAgency.setAgencyName(rescueAgencyForm.getAgencyName());
        rescueAgency.setAgencyLocation(rescueAgencyForm.getAgencyLocation());
        rescueAgency.setContactPersonName(rescueAgencyForm.getContactPersonName());
        rescueAgency.setContactNumber(rescueAgencyForm.getContactNumber());
        rescueAgency.setEmail(rescueAgencyForm.getEmail());
        rescueAgency.setApproved(false);
        rescueAgency.setAvailability(rescueAgencyForm.getAvailability());
        rescueAgency.setPassword(rescueAgencyForm.getPassword());
        rescueAgency.setLatitude(rescueAgencyForm.getLatitude());
        rescueAgency.setLongitude(rescueAgencyForm.getLongitude());
        rescueAgency.setConfirmPassword(rescueAgencyForm.getConfirmPassword());
        rescueAgency.setSpecialization(rescueAgencyForm.getSpecialization());
        rescueAgency.setDescription(rescueAgencyForm.getDescription());
        rescueAgency.setTeamSize(rescueAgencyForm.getTeamSize());

        RescueAgency savedRescueAgency = rescueAgencyService.saveAgency(rescueAgency);
        if (savedRescueAgency == null) {

            Message fail = Message.builder()
            .content("Email already exists!")
            .type(MessageType.red)
            .build();
            session.setAttribute("message", fail); // Store the message in the session

            return "redirect:/college/register"; // Redirect to the registration page
        }

        // Message for successful registration
        Message message = Message.builder()
                                 .content("Registration Successful Wait For Admin Approval") // Fix the spelling mistake here
                                 .type(MessageType.green)
                                 .build();
        session.setAttribute("message", message); // Store the message in the session

        return "redirect:/college/login"; // Redirect to login page
    }

    @PostMapping("/admin/approve_agencies")
    public String approveAgency(@RequestParam Long agencyId, Model model) {
        rescueAgencyService.approveAgency(agencyId);
        model.addAttribute("message", "Agency approved successfully!");
        return "redirect:/admin/approve_agencies";
    }

    @GetMapping("/logout")
    public String logout(Authentication authentication) {
        // Logic for logging out (if necessary)
        return "redirect:/login";
    }

    @GetMapping("/college/dashboard")
    public String agencyDashboard(Authentication authentication, Model model) {
        if (authentication != null) {
            String email = authentication.getUsername();
            RescueAgency agency = rescueAgencyService.findByEmail(email);
            if (agency != null) {
                model.addAttribute("agency", agency);
            } else {
                model.addAttribute("error", "Agency not found.");
            }
        } else {
            model.addAttribute("error", "You must be logged in to view this page.");
        }
        return "agency_dashboard";
    }
}
