package com.skyllx.system.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.skyllx.system.entity.TechnolgyListEntity;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class TechnologyRepoImpl implements TechnologyRepo {

	@Autowired
	private EntityManagerFactory factory;

	@Override
	public boolean add(TechnolgyListEntity entity) {
		log.info("add in TechnologyRepoImpl");
		EntityManager entityManager = this.factory.createEntityManager();
		try {
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(entity);
			transaction.commit();
			return true;
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return false;
	}
	
	@Override
	public List<TechnolgyListEntity> listById(int id) {
		log.info("listById + "+id);
		EntityManager entityManager = this.factory.createEntityManager();
		try {
			Query query = entityManager.createNamedQuery("list");
			Query setParameter = query.setParameter("byId", id);
			log.info("setParameter : "+setParameter);
			List<TechnolgyListEntity> resultList = query.getResultList();
			resultList.forEach(l->log.info(""+l));
			return resultList;
		} catch (PersistenceException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return TechnologyRepo.super.listById(id);
	}

}
