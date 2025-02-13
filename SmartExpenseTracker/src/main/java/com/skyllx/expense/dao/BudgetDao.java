package com.skyllx.expense.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skyllx.expense.model.Budget;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public class BudgetDao {

	@Autowired
	private SessionFactory sessionFactory;

    // Return the Budget object instead of just the monthly limit
    public Budget getBudgetForUser(int userId) {
        Session session = sessionFactory.openSession(); // Open session manually
        Transaction tx = session.beginTransaction(); // Start transaction

        Budget budget = session.createQuery("FROM Budget WHERE user.id = :userId", Budget.class)
                .setParameter("userId", userId)
                .uniqueResult();

        tx.commit(); // Commit transaction
        session.close(); // Close session

        return budget; // Return the Budget object (or null if not found)
    }


	public void saveOrUpdateBudget(Budget budget) {
		Session session = sessionFactory.openSession(); // Open session manually
		Transaction tx = session.beginTransaction(); // Start transaction

		session.saveOrUpdate(budget); // Save or update the budget

		tx.commit(); // Commit transaction
		session.close(); // Close session
	}
}
