package com.skyllx.expense.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.skyllx.expense.dao.ExpenseDao;
import com.skyllx.expense.model.Expense;
import com.skyllx.expense.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class ExpenseDataController {

	@Autowired
	private ExpenseDao expenseDao;

	@GetMapping("/expenseData")
	public String getExpenseData(HttpSession session, Model model) {
	    // Get the logged-in user from session
	    User user = (User) session.getAttribute("loggedUser");

	    if (user == null) {
	        return "redirect:/login"; // Redirect if not logged in
	    }
		
		List<Expense> expenses = expenseDao.getExpensesByUser(user.getUsername());
		expenses.forEach(e -> System.out.println(e.getCategory().getName() + " - " + e.getAmount()));
		Map<String, Double> categoryTotals = new HashMap<>();

		for (Expense expense : expenses) {
			if (expense.getCategory() != null) { // Ensure category is not null
				String category = expense.getCategory().getName();
				categoryTotals.put(category, categoryTotals.getOrDefault(category, 0.0) + expense.getAmount());
			}
		}

		model.addAttribute("categoryWiseSpends", categoryTotals);
		return "viewReports"; // JSP file name: viewReports.jsp
	}
}
