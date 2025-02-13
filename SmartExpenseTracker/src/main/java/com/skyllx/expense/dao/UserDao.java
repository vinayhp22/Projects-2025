package com.skyllx.expense.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.skyllx.expense.model.User;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public User findUserByUsername(String username) {

		Session session = sessionFactory.openSession(); // Open session manually
		Transaction tx = session.beginTransaction(); // Start transaction

		User user = session.createQuery("from User u where u.username = :username", User.class)
			    .setParameter("username", username).uniqueResult();
		
		tx.commit(); // Commit transaction
		session.close(); // Close session
		return user;
	}

	public void saveUser(User user) {
	    Session session = sessionFactory.openSession();  // Open a new session
	    Transaction tx = session.beginTransaction();  // Start transaction
	    session.save(user);
	    tx.commit();  // Commit the transaction
	    session.close();  // Close the session
	}

}
