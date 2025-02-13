package com.skyllx.expense.service;

import com.skyllx.expense.dao.ExpenseDao;
import com.skyllx.expense.model.Categories;
import com.skyllx.expense.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RecurringTransactionService {

    @Autowired
    private ExpenseDao expenseDao;

    @Scheduled(cron = "0 0 1 * * ?") // Runs on the 1st of every month
    public void addRecurringExpenses() {
        Expense subscription = new Expense();
        Categories categories = new Categories();
        categories.setName("Subscription");
        subscription.setCategory(categories);
        subscription.setAmount(500);
        subscription.setDate(new Date());

        expenseDao.saveExpense(subscription);
    }
}
