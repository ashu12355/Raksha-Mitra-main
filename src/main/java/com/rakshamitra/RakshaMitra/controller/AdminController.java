package com.rakshamitra.RakshaMitra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rakshamitra.RakshaMitra.service.RescueAgencyService;

@Controller
public class AdminController {
    @Autowired
    private RescueAgencyService rescueAgencyService;

    @GetMapping("/admin/login")
    public String showAdminLoginPage() {
        return "admin_login"; // Show login page
    }

    @GetMapping("/admin/admin_dashboard")
    public String showAdminDashboard() {
        return "admin_dashboard"; // Admin dashboard page
    }

    @GetMapping("/admin/approve_agencies")
    public String showAgencyApprovalPage(Model model) {
        model.addAttribute("agencies", rescueAgencyService.findAllPendingAgencies());
        return "admin_approve_agencies"; // View that lists pending agencies
    }

}
