package com.skyllx.parkingrental.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.skyllx.parkingrental.dto.ParkingDTO;
import com.skyllx.parkingrental.dto.ParkingInfoDTO;
import com.skyllx.parkingrental.dto.UserDTO;
import com.skyllx.parkingrental.dto.UserParkingDTO;

public interface ParkingService {

	default ParkingDTO validateCredential(String email, String password) {
		return null;
	}

	default ParkingDTO findById(int id) {
		return null;
	}

	default boolean saveParkingInfo(ParkingInfoDTO dto) {
		return false;
	}

	default ParkingInfoDTO isExist(String location, String vehicleType, String engineType, String classification, String term) {
		return null;
	}

	default List<ParkingInfoDTO> findByLocation(String location){
		return Collections.emptyList();
	}
	
	//user
	default boolean validateAndRegister(UserDTO userDto, UserParkingDTO uParkingDto) {
		return false;
	}
	
	default boolean isUserExist(String email) {
		return false;
	}
	
	default boolean generateOtp(String email) throws IOException {
		return false;
	}
	
	default boolean validateOTP(String email, Integer otp) {
		return false;
	}
	
	default String validateAccountStatus(int otpCount) {
		return null;
	}
	default UserDTO findByUserEmail(String email) {
		return null;
	}
	
	default List<UserParkingDTO> findAllById(String email){
		return Collections.emptyList();
	}
	
	default UserParkingDTO findByVehicleNo(String vehicleNo) {
		return null;
	}
	
	default boolean addUserParkingInfo(UserParkingDTO uParkingDto, String email) {
		return false;
	}
	
	default boolean updateUserParkingInfo(UserParkingDTO upDto) {
		return false;
	}
	
	default boolean updatePayment(String email) {
		return false;
	}
	
	default boolean deleteUserParkingEntityByVehicleNo(String vNo) {
		return false;
	}
	
	default Map<String, Long> findPamentDueDays(String email){
		return null;
	}
	
	default List<UserParkingDTO> findAll(){
		return Collections.emptyList();
	}

}
