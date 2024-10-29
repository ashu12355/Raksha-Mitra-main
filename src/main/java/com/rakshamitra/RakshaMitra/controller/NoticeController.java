package com.rakshamitra.RakshaMitra.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.rakshamitra.RakshaMitra.model.Notice;
import com.rakshamitra.RakshaMitra.service.NoticeService;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    // Show Add Notice Page
    @GetMapping("/college/notice/add")
    public String showAddNoticePage() {
        return "add_notice";  // Ensure this template exists
    }

    // Handle Notice Submission
  // Handle Notice Submission
@PostMapping("/college/notice/add")
public String addNotice(
        @RequestParam String title,
        @RequestParam String description,
        @RequestParam String eventType, // New parameter for event type
        @RequestParam(required = false) String address, // Optional address parameter
        @RequestParam(required = false) Double registrationFee, // Optional registration fee
        HttpSession session) {
    
    // Ensure the session contains a valid agencyId
    Long agencyId = (Long) session.getAttribute("agencyId");
    if (agencyId == null) {
        throw new IllegalStateException("Agency ID is missing from session");
    }

    // Create a new Notice object using the provided parameters
    Notice notice = Notice.builder()
            .eventName(title) 
            .eventType(eventType) // Set the event type from the request parameter
            .organizer("Agency Organizer") // Default organizer name; replace if applicable
            .startTime(LocalTime.now()) // Current time as start time; adjust if necessary
            .endTime(LocalTime.now().plusHours(2)) // End time set to 2 hours later; adjust as needed
            .title(title)
            .address(address != null ? address : "Not Provided") // Use provided address or default
            .registrationLink("Not Provided") // Placeholder for registration link; replace if necessary
            .registrationFee(registrationFee != null ? registrationFee : 0.0) // Use provided fee or default
            .description(description)
            .startDate(LocalDate.now()) // Today's date as start date
            .endDate(LocalDate.now().plusDays(1)) // Set end date to tomorrow; adjust as needed
            .agencyId(agencyId) 
            .build();
    noticeService.saveNotice(notice); 

    // Redirect or return a view after successful submission
    return "redirect:/college/notice/success"; // Adjust the redirect path as needed
}


    // View Recent Notices for Students
    @GetMapping("/student/notices")
    public String showAllNotices(Model model) {
        model.addAttribute("notices", noticeService.getAllNotices());  // Show all notices
        return "student_notices";  // Ensure this template exists
    }

    @GetMapping("/college/notice/success")
    public String success(){
        return "agency_dashboard";
    }
    
}

