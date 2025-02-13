package com.skyllx.expense.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skyllx.expense.dao.BudgetDao;
import com.skyllx.expense.dao.CategoryDao;
import com.skyllx.expense.dao.ExpenseDao;
import com.skyllx.expense.model.Categories;
import com.skyllx.expense.model.Expense;
import com.skyllx.expense.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class ExpenseController {

	@Autowired
	private ExpenseDao expenseDao;

	@Autowired
	private BudgetDao budgetDao;

	@Autowired
	private CategoryDao categoryDao;

	@GetMapping("/addExpense")
	public String showAddExpensePage(HttpSession session, Model model) {
		// Get the logged-in user from session
		User user = (User) session.getAttribute("loggedUser");

		if (user == null) {
			return "redirect:/login"; // Redirect if not logged in
		}

		List<Categories> categories = categoryDao.getAllCategories();
		model.addAttribute("categories", categories);
		return "addExpense"; // This maps to addExpense.jsp
	}

	@PostMapping("/addExpense")
	public String addExpense(@RequestParam("category") String categoryName, @RequestParam("amount") double amount,
			@RequestParam(value = "description", required = false) String description, HttpSession session, Model model,
			RedirectAttributes redirectAttributes) {
		// Get the logged-in user from session
		User user = (User) session.getAttribute("loggedUser");

		if (user == null) {
			return "redirect:/login"; // Redirect if not logged in
		}

		// Find or create the category
		Categories category = categoryDao.findByName(categoryName);
		if (category == null) {
			// If the category doesn't exist, create a new one
			category = new Categories();
			category.setName(categoryName);
			categoryDao.saveCategory(category);
		}

		// Create and save expense
		Expense expense = new Expense();
		expense.setUser(user);
		expense.setCategory(category);
		expense.setAmount(amount);
		expense.setDate(new Date()); // Set the current date

		expenseDao.saveExpense(expense);

		// Check if budget limit is exceeded
		double totalSpent = expenseDao.getTotalExpenseForUser(user.getUsername());
		double budgetLimit = budgetDao.getBudgetForUser(user.getId()).getMonthlyLimit();

		if (totalSpent > 0.8 * budgetLimit) {
			model.addAttribute("warning", "Warning: You have spent over 80% of your budget!");
		}

		redirectAttributes.addFlashAttribute("success", "Your Expense Added successfully!");

		return "redirect:/expenses";
	}
}
