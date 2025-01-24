package com.skyllx.parkingrental.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.skyllx.parkingrental.dto.ParkingDTO;
import com.skyllx.parkingrental.dto.ParkingInfoDTO;
import com.skyllx.parkingrental.dto.UserDTO;
import com.skyllx.parkingrental.dto.UserParkingDTO;
import com.skyllx.parkingrental.util.GenerateOTP;
import com.skyllx.parkingrental.util.ParkingEmail;
import com.skyllx.parkingrental.util.UserOTPMail;
import com.skyllx.parkingrental.util.UserParkingEmail;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyllx.parkingrental.entity.ParkingEntity;
import com.skyllx.parkingrental.entity.ParkingInfoEntity;
import com.skyllx.parkingrental.entity.UserEntity;
import com.skyllx.parkingrental.entity.UserParkingEntity;
import com.skyllx.parkingrental.repository.ParkingRepo;
import com.skyllx.parkingrental.util.DateDifference;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ParkingServiceImpl implements ParkingService{

	@Autowired
	private ParkingRepo repo;
	@Autowired
	private ParkingEmail parkingEmail;
	@Autowired
	private UserParkingEmail userParkingEmail;
	@Autowired
	private UserOTPMail userOTPMail;

	public ParkingServiceImpl() {
		log.info("Created: " + getClass().getSimpleName());
	}

	public ParkingDTO validateCredential(String email, String password) {
		log.info("running validateCredential()");
		if (email != null && password != null) {
			log.info("dto is not null");
			ParkingEntity entity = repo.findByEmail(email);
			if (entity != null) {
				if (entity.getAdminEmail().equals(email) && entity.getPassword().equals(password)) {

					// sending email
					parkingEmail.sendMail(entity.getAdminEmail(), entity.getName());

					// setting current time
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy hh:mm aa");
					String formattedDate = dateFormat.format(new Date()).toString();

					// updating time in db
					entity.setLoginTime(formattedDate);
					repo.updateLoginTime(entity);

					// getting entity with login-time
					ParkingEntity entity2 = repo.findByEmail(email);

					// coping entity to dto
					ParkingDTO dto2 = new ParkingDTO();
					BeanUtils.copyProperties(entity2, dto2);
					return dto2;
				} else {
					log.info("Service: invalid credential");
				}
			} else {
				log.info("Service: No matching record found by email");
			}
		} else {
			log.info("Service: email & password are null");
		}
		return null;
	}

	public ParkingInfoDTO isExist(String location, String vehicleType, String engineType, String classification,
                                  String term) {
		log.info("running isExist() : "+location + " - " + vehicleType + " - " + engineType + " - " + classification + " - " + term);
		ParkingInfoEntity record = repo.findByLocationAndVehicleTypeAndEngineTypeAndClsAndTerm(location, vehicleType,
				engineType, classification, term);
		if (record != null) {
			log.info("data is already exist");
			ParkingInfoDTO dto = new ParkingInfoDTO();
			BeanUtils.copyProperties(record, dto);
			return dto;
		}
		return null;
	}

	public boolean saveParkingInfo(ParkingInfoDTO dto) {
		log.info("running isExist()");
		ParkingInfoEntity record = repo.findByLocationAndVehicleTypeAndEngineTypeAndClsAndTerm(dto.getLocation(),
				dto.getVehicleType(), dto.getEngineType(), dto.getClassification(), dto.getTerm());
		if (record != null) {
			log.info("data is already exist");
			return false;
		} else {
			log.info("data does not exist in db, saving new record");
			ParkingInfoEntity entity = new ParkingInfoEntity();
			BeanUtils.copyProperties(dto, entity);
			boolean saved = repo.saveParkingInfo(entity);
			if (saved) {
				log.info("Service: data saved");
				return true;
			}
			log.info("Service: data not saved");
			return false;
		}
	}

	public List<ParkingInfoDTO> findByLocation(String location) {
		log.info("service: running findByLocation() from ParkingServiceImpl");
		List<ParkingInfoEntity> entityList = repo.findByLocation(location);
		if (!entityList.isEmpty()) {
			List<ParkingInfoDTO> dtoList = entityList.stream().map(entity -> {
				ParkingInfoDTO dto = new ParkingInfoDTO();
				BeanUtils.copyProperties(entity, dto);
				return dto;
			}).collect(Collectors.toList());
			return dtoList;
		}
		return Collections.emptyList();
	}

	// for user
	public boolean isUserExist(String email) {
		log.info("running isUserEmailExist()");
		UserEntity entity = repo.findByUserEmail(email);
		log.info("service: isUserExist: " + entity);
		if (entity != null) {
			return true;
		}
		return false;
	}

	public boolean validateAndRegister(UserDTO userDto, UserParkingDTO upDto) {
		log.info("running validateAndRegister()");

		UserEntity record = repo.findByUserEmail(userDto.getEmail());
		if (record == null) {
        // Use Java 8+ LocalDateTime for better date handling
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);

			userDto.setCreatedBy(userDto.getName());
			userDto.setCreatedDate(formattedDate);
			userDto.setUpdatedBy(userDto.getName());
			userDto.setUpdatedDate(formattedDate);
		//	userDto.setOtp(null);
			userDto.setOtpCount(0);
			userDto.setAcctStatus("Active");
			
			log.info("validateAndRegister: userDto: " + userDto);
			UserEntity userEntity = new UserEntity();
			BeanUtils.copyProperties(userDto, userEntity);
			repo.saveUserData(userEntity);

			UserEntity userByEmail = repo.findByUserEmail(userDto.getEmail());
			
			log.info("userByEmail: "+userByEmail);
			upDto.setUserId(userByEmail.getId());
			upDto.setPayment("pending");
			upDto.setCreatedDate(formattedDate);
			upDto.setUpdatedDate(formattedDate);
			upDto.setActive(true);
			
			UserParkingEntity upEntity = new UserParkingEntity();
			BeanUtils.copyProperties(upDto, upEntity);
			repo.saveUserParkingInfo(upEntity);
			userParkingEmail.sendMail(userDto.getEmail(), userDto.getName(), upDto);
			return true;
		}
		return false;
	}

	@Transactional
	public boolean generateOtp(String email) throws IOException 
	{
		log.info("running generateOtp()");
		int otp = GenerateOTP.generateOtp(); // generated otp
		log.info("service: generateOtp: otp: " + otp);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
		String formattedDate = dateFormat.format(new Date());
		log.info("service: generateOtp: formattedDate: " + formattedDate);
		UserEntity entity = repo.findByUserEmail(email);
		entity.setUpdatedDate(formattedDate);
	//	String encryptedOtp = OtpEncryptAndDecrypt.enryptOtp(otp);
//		log.info("service: generateOtp: encryptedOtp: " + encryptedOtp);
//		entity.setOtp(encryptedOtp);
		entity.setOtp(otp);
		entity.setOtpCount(0);
		entity.setAcctStatus("Active");
		entity.setOtpExpiryTime(LocalTime.now().plusSeconds(120));
		
		if (repo.updateUserEntity(entity) && userOTPMail.sendMail(email, entity.getName(), otp))																							// send email
		{
			log.info("otp generated & sent to registered email");
			return true;
		} else {
			log.info("otp not generated & sent to registered email");
			return false;
		}
	}

	public boolean validateOTP(String email, Integer otp) {
		log.info("running validateOTP()");
		UserEntity entity = repo.findByUserEmail(email);
		int otpCount = entity.getOtpCount();
		if (entity.getAcctStatus().equals("Active")) {
			log.info("service: validateOTP(): acct is active");
			if (entity.getOtp().equals(otp)) {
				log.info("valid otp");
				return true;
			} else {
				log.info("service: validateOTP(): invalid otp");
				otpCount++;
				log.info("service: validateOTP(): otp count: " + otpCount);
				if (otpCount < 3) {
					log.info("service: validateOTP(): otp count is < 3");
					entity.setOtpCount(otpCount);
					repo.updateUserEntity(entity);
					log.info("invalid otp");
					return false;
				} else {
					log.info("service: validateOTP(): otp count is = 3");
					entity.setOtpCount(otpCount);
					entity.setAcctStatus("Blocked");
					repo.updateUserEntity(entity);
					log.info("Acccount is blocked");
					return false;
				}
			}
		}
		log.info("Acccount is blocked");
		return false;
	}

	public UserDTO findByUserEmail(String email) {
		log.info("running findByUserEmail()");
		UserEntity entity = repo.findByUserEmail(email);
		if (entity != null) {
			UserDTO dto = new UserDTO();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}
		return null;
	}	

	public List<UserParkingDTO> findAllById(String email) {				
		log.info("running findAllById()");
		UserEntity userEntity = repo.findByUserEmail(email);
		List<UserParkingEntity> upEntities = repo.findAllByUserId(userEntity.getId());
		log.info("service: findAllById: upList: " + upEntities);
		if (!upEntities.isEmpty()) {
			List<UserParkingDTO> upDtos = upEntities.stream().map(entity -> {
				UserParkingDTO upDto = new UserParkingDTO();
				BeanUtils.copyProperties(entity, upDto);
				return upDto;
			}).collect(Collectors.toList());
			return upDtos;
		}
		return Collections.emptyList();
	}
	
	public UserParkingDTO findByVehicleNo(String vehicleNo) {
		log.info("running findByVehicleNo()");
		UserParkingEntity entity = repo.findByVehicleNo(vehicleNo);
		if (entity != null) {
			UserParkingDTO dto = new UserParkingDTO();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}
		return null;
	}

	public boolean addUserParkingInfo(UserParkingDTO upDto, String email) {
		log.info("running addUserParkingInfo()");
		UserEntity entityByEmail = repo.findByUserEmail(email);
		if (entityByEmail != null) {
			log.info("entityByEmail is not null");

			// Use Java 8+ LocalDateTime for better date handling
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedDate = now.format(formatter);
			
			upDto.setUserId(entityByEmail.getId());
			upDto.setPayment("pending");
			upDto.setCreatedDate(formattedDate);
			upDto.setUpdatedDate(formattedDate);
			upDto.setActive(true);
			
			UserParkingEntity upEntity = new UserParkingEntity();
			BeanUtils.copyProperties(upDto, upEntity);
			repo.saveUserParkingInfo(upEntity);
			userParkingEmail.sendMail(email, entityByEmail.getName(), upDto);
			return true;
		}
		log.info("entityByEmail is not null");
		return false;
	}
	
	public boolean updateUserParkingInfo(UserParkingDTO upDto) {
		log.info("service: running updateUserParkingInfo()");
		log.info("service: running updateUserParkingInfo(): upDto: "+upDto);
		 UserParkingEntity entity = repo.findByVehicleNo(upDto.getVehicleNo());
		if (entity != null) {
			log.info("entity is not null: "+entity);
			
        // Use Java 8+ LocalDateTime for better date handling
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);
		
			entity.setLocation(upDto.getLocation());
			entity.setVehicleType(upDto.getVehicleType());
			entity.setEngineType(upDto.getEngineType());
			entity.setClassification(upDto.getClassification());
			entity.setTerm(upDto.getTerm());
			entity.setPrice(upDto.getPrice());
			entity.setDiscount(upDto.getDiscount());
			entity.setTotalAmount(upDto.getTotalAmount());
			entity.setUpdatedDate(formattedDate);
			
			if(upDto.getFileName()!=null)
			{
				entity.setFileName(upDto.getFileName());
				entity.setOriginalFileName(upDto.getOriginalFileName());
				entity.setContentType(upDto.getContentType());
			}
			boolean updated=repo.updateUserParkingInfo(entity);
			if(updated) {
				log.info("service: updateUserParkingInfo(): updated user parking info");
				return true;
			}
			log.info("service: updateUserParkingInfo(): not updated user parking info");
			return false;
		}
		log.info("service: updateUserParkingInfo(): entity is null");
		return false;
	}
	
	public Map<String, Long> findPamentDueDays(String email) {
		log.info("running findPamentDueDays()");
		UserEntity uEntity = repo.findByUserEmail(email);
		List<UserParkingEntity> upList = repo.findAllByUserId(uEntity.getId());
		
		List<UserParkingEntity> paymentPendingList = upList.stream().filter(e -> e.isActive())
				.filter(e -> e.getPayment().equals("pending")).collect(Collectors.toList());
		log.info("findPamentDueDays(): paymentPendingList: " + paymentPendingList);

		if(!paymentPendingList.isEmpty()) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
		String formattedDate = dateFormat.format(new Date());

		Map<String, Long> dueDaysMap = new LinkedHashMap<String, Long>();
		
		for (UserParkingEntity upEntity : paymentPendingList) {
			long noOfDays = DateDifference.findDaysDifference(upEntity.getCreatedDate(), formattedDate);
			dueDaysMap.put(upEntity.getVehicleNo(), noOfDays);
		}
		log.info("findPamentDueDays(): DueDays Map: " + dueDaysMap);
		return dueDaysMap;
	}
		log.info("findPamentDueDays(): no due");
		return null;
	}
	
		public boolean updatePayment(String vehicleNo) {
		log.info("running updatePayment()");
		 UserParkingEntity entity = repo.findByVehicleNo(vehicleNo);
		 entity.setPayment("paid");
		 boolean updated = repo.updateUserParkingInfo(entity);
		 if(updated) {
			 log.info("payment status updated");
			 return true;
		 }
		log.info("payment status not updated");
		return false;
	}
	
	public boolean deleteUserParkingEntityByVehicleNo(String vehicleNo) {
		log.info("running deleteUserParkingEntityByVehicleNo()");
	//	UserParkingEntity entity = repo.findByVehicleNo(vehicleNo);
	//	if(entity!=null) {
			 if(repo.deleteUserParkingEntity(vehicleNo)) {
					log.info("deleteUserParkingEntityByVehicleNo(): entity deleted");
				 return true;
		}else {
			log.info("deleteUserParkingEntityByVehicleNo(): entity not deleted");
			return false;
		}
	}
	
	public List<UserParkingDTO> findAll(){
		log.info("running findAll()");
		List<UserParkingEntity> entityList = repo.findAll();
		if(entityList!=null) { 
		List<UserParkingDTO> dtoList = entityList.stream().map(entity->{
			UserParkingDTO dto=new UserParkingDTO();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}).collect(Collectors.toList());
		return dtoList;
	}
		return Collections.emptyList();
	}

}
