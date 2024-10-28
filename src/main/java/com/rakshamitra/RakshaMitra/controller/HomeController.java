package com.rakshamitra.RakshaMitra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "RakshaMitra");
        return "home"; 
    }
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    @GetMapping("/ghash")
    public String showGhashPage() {
        return "ghash"; // This maps to ghash.html in templates
    }
    @GetMapping("/reportDisaster")
    public String showReportDisaster() {
        return "reportD";
    }
}
