package com.skyllx.parkingrental.repository;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.skyllx.parkingrental.entity.ParkingEntity;
import com.skyllx.parkingrental.entity.ParkingInfoEntity;
import com.skyllx.parkingrental.entity.UserEntity;
import com.skyllx.parkingrental.entity.UserParkingEntity;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Repository
@Slf4j
public class ParkingRepoImpl implements ParkingRepo {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public boolean updateLoginTime(ParkingEntity entity) {
		log.info("Running updateLoginTime()");
		try {
			manager.merge(entity);
			return true;
		} catch (Exception e) {
			log.error("Error in updateLoginTime", e);
			return false;
		}
	}

	@Override
	public ParkingEntity findByEmail(String email) {
		log.info("Running findByEmail()");
		Query query = manager.createNamedQuery("findByEmail");
		query.setParameter("ss", email);
		try {
			return (ParkingEntity) query.getSingleResult();
		} catch (NoResultException e) {
			log.warn("No record found for email: {}", email);
			return null;
		} catch (Exception e) {
			log.error("Error in findByEmail", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public ParkingInfoEntity findByLocationAndVehicleTypeAndEngineTypeAndClsAndTerm(
			String location, String vehicleType, String engineType, String classification, String term) {
		log.info("Running findByLocationAndVehicleTypeAndEngineTypeAndClsAndTerm()");
		try {
			Query query = manager.createNamedQuery("findByLTTCT");
			query.setParameter("lc", location);
			query.setParameter("vtype", vehicleType);
			query.setParameter("etype", engineType);
			query.setParameter("cls", classification);
			query.setParameter("trm", term);
			return (ParkingInfoEntity) query.getSingleResult();
		} catch (NoResultException e) {
			log.warn("No record found for given parameters.");
			return null;
		} catch (Exception e) {
			log.error("Error in findByLocationAndVehicleTypeAndEngineTypeAndClsAndTerm", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean saveParkingInfo(ParkingInfoEntity entity) {
		log.info("Running saveParkingInfo()");
		try {
			manager.persist(entity);
			return true;
		} catch (PersistenceException e) {
			log.error("Error in saveParkingInfo", e);
			return false;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ParkingInfoEntity> findByLocation(String location) {
		log.info("Running findByLocation()");
		Query query = manager.createNamedQuery("findByLocation");
		query.setParameter("lc", location);
		try {
			return query.getResultList();
		} catch (Exception e) {
			log.error("Error in findByLocation", e);
			return Collections.emptyList();
		}
	}

	@Override
	public UserEntity findByUserEmail(String email) {
		log.info("Running findByUserEmail()");
		Query query = manager.createNamedQuery("findByUserEmail");
		query.setParameter("mail", email);
		try {
			return (UserEntity) query.getSingleResult();
		} catch (NoResultException e) {
			log.warn("No record found for user email: {}", email);
			return null;
		} catch (Exception e) {
			log.error("Error in findByUserEmail", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean saveUserData(UserEntity entity) {
		log.info("Running saveUserData()");
		try {
			manager.persist(entity);
			return true;
		} catch (PersistenceException e) {
			log.error("Error in saveUserData", e);
			return false;
		}
	}

	@Override
	public boolean saveUserParkingInfo(UserParkingEntity entity) {
		log.info("Running saveUserParkingInfo()");
		try {
			manager.persist(entity);
			return true;
		} catch (PersistenceException e) {
			log.error("Error in saveUserParkingInfo", e);
			return false;
		}
	}

	@Override
	public boolean updateUserEntity(UserEntity entity) {
		log.info("Running updateUserEntity()");
		try {
			manager.merge(entity);
			return true;
		} catch (Exception e) {
			log.error("Error in updateUserEntity", e);
			return false;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserParkingEntity> findAllByUserId(Integer id) {
		log.info("Running findAllByUserId()");
		Query query = manager.createNamedQuery("findAllByUserId");
		query.setParameter("uId", id);
		query.setParameter("status", true);
		try {
			return query.getResultList();
		} catch (Exception e) {
			log.error("Error in findAllByUserId", e);
			return Collections.emptyList();
		}
	}

	@Override
	public UserParkingEntity findByVehicleNo(String vehicleNo) {
		log.info("Running findByVehicleNo()");
		Query query = manager.createNamedQuery("findByVehicleNo");
		query.setParameter("vNo", vehicleNo);
		try {
			return (UserParkingEntity) query.getSingleResult();
		} catch (NoResultException e) {
			log.warn("No record found for vehicle number: {}", vehicleNo);
			return null;
		} catch (Exception e) {
			log.error("Error in findByVehicleNo", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean updateUserParkingInfo(UserParkingEntity entity) {
		log.info("Running updateUserParkingInfo()");
		try {
			manager.merge(entity);
			return true;
		} catch (Exception e) {
			log.error("Error in updateUserParkingInfo", e);
			return false;
		}
	}

	@Override
	public boolean deleteUserParkingEntity(String vehicleNo) {
		log.info("Running deleteUserParkingEntity()");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
		String formattedDate = dateFormat.format(new Date());

		Query query = manager.createNamedQuery("findByVehicleNo");
		query.setParameter("vNo", vehicleNo);

		try {
			UserParkingEntity entity = (UserParkingEntity) query.getSingleResult();
			if (entity != null) {
				entity.setActive(false);
				entity.setUpdatedDate(formattedDate);
				manager.merge(entity);
				return true;
			}
		} catch (Exception e) {
			log.error("Error in deleteUserParkingEntity", e);
			return false;
		}
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserParkingEntity> findAll() {
		log.info("Running findAll()");
		Query query = manager.createNamedQuery("findAllEntities");
		try {
			return query.getResultList();
		} catch (Exception e) {
			log.error("Error in findAll", e);
			return Collections.emptyList();
		}
	}
}
