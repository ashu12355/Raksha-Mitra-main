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
    @PostMapping("/college/notice/add")
    public String addNotice(
            @RequestParam String title,
            @RequestParam String content,
            HttpSession session) {
    
        // Ensure the session contains a valid agencyId
        Long agencyId = (Long) session.getAttribute("agencyId");
        if (agencyId == null) {
            throw new IllegalStateException("Agency ID is missing from session");
        }
    
        Notice notice = Notice.builder()
                .title(title)
                .content(content)
                .date(LocalDate.now())
                .agencyId(agencyId)  // Properly store the agencyId here
                .build();
    
        noticeService.saveNotice(notice);
        return "redirect:/college/dashboard";
    }
    

    // View Recent Notices for Students
    @GetMapping("/student/notices")
    public String showStudentNotices(Model model) {
        model.addAttribute("notices", noticeService.getAllNotices());  // Show all notices
        return "student_notices";  // Ensure this template exists
    }
}

