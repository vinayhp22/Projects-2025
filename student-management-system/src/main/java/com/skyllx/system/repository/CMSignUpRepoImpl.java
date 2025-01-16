package com.skyllx.system.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import com.skyllx.system.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Scope
public class CMSignUpRepoImpl implements CMSignUpRepo {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public boolean save(UserEntity entity) {
		log.info("save in CMSignUpRepoImpl" + entity);
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		try {
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.persist(entity);
			entityTransaction.commit();
			return true;
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return false;
	}

	@Override
	public Long countByUserId(String userId) {
		log.info("countByUserId(String userId) in repo " + userId);
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		try {
			Query query = entityManager.createNamedQuery("countByUserId");
			query.setParameter("byUserId", userId);
			Long result = (Long) query.getSingleResult();
			log.info("countByUserId(String userId) "+userId+" - "+result);
			return result;
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return 0l;
	}

	@Override
	public Long countByEmail(String email) {
		log.info("countByEmail(String email) in repo " + email);
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		try {
			Query query = entityManager.createNamedQuery("countByEmail");
			query.setParameter("byEmail", email);
			Long result = (Long) query.getSingleResult();
			log.info("countByEmail(String email) "+email+" - "+result);
			return result;
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return 0l;
	}

	@Override
	public Long countByMobile(Long mobile) {
		log.info("countByMobile(Long mobile) in repo " + mobile);
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		try {
			Query query = entityManager.createNamedQuery("countByMobile");
			query.setParameter("byMobile", mobile);
			Long result = (Long) query.getSingleResult();
			log.info("countByMobile(Long mobile) "+mobile+" - "+result);
			return result;
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return 0l;
	}

}
