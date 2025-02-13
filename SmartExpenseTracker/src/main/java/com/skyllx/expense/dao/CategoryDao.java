package com.skyllx.expense.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skyllx.expense.model.Categories;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Categories findByName(String name) {
		Session session = sessionFactory.openSession(); // Open session manually
		Transaction transaction = session.beginTransaction(); // Start transaction
        Categories uniqueResult = session.createQuery("FROM Categories WHERE name = :name", Categories.class).setParameter("name", name).uniqueResult();
        
        transaction.commit();
        session.close();
        
        return uniqueResult;
    }

    public void saveCategory(Categories category) {

		Session session = sessionFactory.openSession(); // Open session manually
		Transaction transaction = session.beginTransaction(); // Start transaction
    	
    	session.save(category);
        transaction.commit();
        session.close();
    }
    
    public List<Categories> getAllCategories() {
        Session session = sessionFactory.openSession(); // Open session manually
        Transaction transaction = session.beginTransaction(); // Start transaction

        // Fetch all categories
        List<Categories> categories = session.createQuery("FROM Categories", Categories.class).list();

        transaction.commit(); // Commit transaction
        session.close(); // Close session

        return categories;
    }
}
