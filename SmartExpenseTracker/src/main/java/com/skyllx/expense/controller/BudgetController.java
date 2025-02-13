package com.skyllx.expense.controller;

import com.skyllx.expense.dao.BudgetDao;
import com.skyllx.expense.model.Budget;
import com.skyllx.expense.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BudgetController {

    @Autowired
    private BudgetDao budgetDao;

    @GetMapping("/setBudget")
    public String setBudget() {
    	return "setBudget";
    }
    
    
    @PostMapping("/setBudget")
    public String setBudget(@RequestParam("budget") double budget, HttpSession session, RedirectAttributes redirectAttributes) {
        
        User user = (User) session.getAttribute("loggedUser");
        if (user == null) {
            return "redirect:/login";
        }
        
        // Fetch the budget for the logged-in user
        Budget userBudget = budgetDao.getBudgetForUser(user.getId());
        if (userBudget == null) {
            // If no budget exists for the user, create a new one
            userBudget = new Budget();
            userBudget.setUser(user);
        }

        userBudget.setMonthlyLimit(budget);
        
        budgetDao.saveOrUpdateBudget(userBudget);
        
        redirectAttributes.addFlashAttribute("success", "Budget set successfully!");
        return "redirect:/expenses";
    }
}
