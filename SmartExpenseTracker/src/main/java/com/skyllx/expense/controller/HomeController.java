package com.skyllx.expense.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHomePage() {
        return "index";  // Loads index.jsp
    }
    
    @GetMapping("/expenses")
    public String showDashboard() {
        return "expenses";  // Loads expenses.jsp (dashboard)
    }

}
