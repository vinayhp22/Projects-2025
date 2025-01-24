package com.skyllx.parkingrental.repository;

import java.util.Collections;
import java.util.List;

import com.skyllx.parkingrental.entity.ParkingEntity;
import com.skyllx.parkingrental.entity.ParkingInfoEntity;
import com.skyllx.parkingrental.entity.UserEntity;
import com.skyllx.parkingrental.entity.UserParkingEntity;

public interface ParkingRepo {
	
	default boolean updateLoginTime(ParkingEntity entity) {
		return false;
	}
	
	default List<ParkingEntity> find(){
		return Collections.emptyList();
	}
	
	default ParkingEntity findByEmail(String email) {
		return null;
	}
	
	//to update parking info
	default ParkingInfoEntity findByLocationAndVehicleTypeAndEngineTypeAndClsAndTerm(String location, String vehicleType, String engineType, String classification, String term) {
		return null;
	}
	
	default boolean saveParkingInfo(ParkingInfoEntity entity) {
		return false;
	}
	
	default List<ParkingInfoEntity> findByLocation(String location){
		return Collections.emptyList();		
	}
	
	//for user model
	default UserEntity findByUserEmail(String email) {
		return null;
	}
	
	default boolean saveUserData(UserEntity entity) {
		return false;
	}
	
	default boolean saveUserParkingInfo(UserParkingEntity entity) {
		return false;
	}

	
	default boolean updateUserEntity(UserEntity entity) {
		return false;
	}
	
	default List<UserParkingEntity> findAllByUserId(Integer id){
		return Collections.emptyList();
	}

	default UserParkingEntity findByVehicleNo(String vehicleNo) {
		return null;
	}
	
	default boolean updateUserParkingInfo(UserParkingEntity entity) {
		return false;
	}
	
	default boolean deleteUserParkingEntity(String vehicleNo) {
		return false;
	}
	
	default List<UserParkingEntity> findAll(){
		return Collections.emptyList();
	}
}
