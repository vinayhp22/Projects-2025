package com.skyllx.expense.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skyllx.expense.model.Expense;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ExpenseDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void saveExpense(Expense expense) {

		Session session = sessionFactory.openSession(); // Open session manually
		Transaction tx = session.beginTransaction(); // Start transaction

		session.save(expense);

		tx.commit(); // Commit transaction
		session.close(); // Close session
	}

	public List<Expense> getExpenses() {

		Session session = sessionFactory.openSession(); // Open session manually
		Transaction tx = session.beginTransaction(); // Start transaction

		List<Expense> list = sessionFactory.getCurrentSession().createQuery("from Expense", Expense.class).list();

		tx.commit(); // Commit transaction
		session.close(); // Close session

		return list;
	}

	public double getTotalExpense() {

		Session session = sessionFactory.openSession(); // Open session manually
		Transaction tx = session.beginTransaction(); // Start transaction

		Double total = (Double) session.createQuery("SELECT SUM(amount) FROM Expense").uniqueResult();

		tx.commit(); // Commit transaction
		session.close(); // Close session

		return total != null ? total : 0.0;
	}

	public List<Expense> getAllExpenses() {

		Session session = sessionFactory.openSession(); // Open session manually
		Transaction tx = session.beginTransaction(); // Start transaction

		List<Expense> list = session.createQuery("FROM Expense", Expense.class).list();
		list.forEach(e->System.out.println(e.getCategory().getName()));
		tx.commit(); // Commit transaction
		session.close(); // Close session

		return list;
	}

	public double getTotalExpenseForUser(String username) {

		Session session = sessionFactory.openSession(); // Open session manually
		Transaction tx = session.beginTransaction(); // Start transaction

		Double total = (Double) session.createQuery("SELECT SUM(amount) FROM Expense WHERE user.username = :username")
				.setParameter("username", username).uniqueResult();
		
		tx.commit(); // Commit transaction
		session.close(); // Close session

		return total != null ? total : 0.0;
	}
	
    public List<Expense> getExpensesByUser(String username) {
		Session session = sessionFactory.openSession(); // Open session manually
		Transaction tx = session.beginTransaction(); // Start transaction
		
		List<Expense> list = session.createQuery("FROM Expense e WHERE e.user.username = :username", Expense.class)
                      .setParameter("username", username)
                      .list();
		
		tx.commit(); // Commit transaction
		session.close(); // Close session
		
		return list;
    }

}
