package com.skyllx.system.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.skyllx.system.entity.UserEntity;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class CMUpdateProfileRepoImpl implements CMUpdateProfileRepo {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public boolean update(UserEntity entity) {
		log.info("updateRandomPassword in repo");

		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		try {
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.merge(entity);
			entityTransaction.commit();
			return true;
		} finally {
			entityManager.close();
		}
	}

}
