package com.skyllx.parkingrental.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.skyllx.parkingrental.dto.ParkingDTO;
import com.skyllx.parkingrental.dto.ParkingInfoDTO;
import com.skyllx.parkingrental.dto.UserDTO;
import com.skyllx.parkingrental.dto.UserParkingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import com.skyllx.parkingrental.constant.FileConstant;
import com.skyllx.parkingrental.service.ParkingService;

import lombok.extern.slf4j.Slf4j;

@Component
@RequestMapping("/")
@Slf4j
public class ParkingController {

	@Autowired
	private ParkingService service;

	public ParkingController() {
		log.info("Created: " + getClass().getSimpleName());
	}
	
	@PostConstruct
	public void init() {
		log.info("Application started");
	}

	@PostMapping("adminLogin")
	public String validate(ParkingDTO dto, Model model, HttpServletRequest req) {
		// if(dto!=null)
		if (!dto.getEmail().isEmpty() && !dto.getPassword().isEmpty()) {
			log.info("running parking()");
			ParkingDTO dto2 = service.validateCredential(dto.getEmail(), dto.getPassword());
			if (dto2 != null) {
				// session scope
				HttpSession session = req.getSession();
				session.setAttribute("sessionDTO", dto2);

				// request scope
				// model.addAttribute("time", dto2.getLoginTime());
				// model.addAttribute("name", dto2.getName());
				return "AdminLoginSuccess";
			} else {
				model.addAttribute("error", "*Invalid credential");
			}
		} else {
			model.addAttribute("error2", "Please enter credential");
		}
		return "AdminLogin";
	}

	@RequestMapping(value = "updateParkingInfo", method = RequestMethod.POST)
//	@PostMapping("updateParkingInfo")
	public String updateParkingInfo(ParkingInfoDTO dto, Model model) {
		log.info("running updateParkingInfo()");
		boolean saved = service.saveParkingInfo(dto);
		if (saved) {
			model.addAttribute("success", "Data saved successfully");
		} else {
			log.info("Data already exist");
			model.addAttribute("error", "Data already exist");
			model.addAttribute("dto2", dto);
		}
		return "UpdateParkingInfo";
	}

	@RequestMapping("viewData")
	public String viewData(String location, Model model) {
		log.info("running viewData()");
		List<ParkingInfoDTO> dtoList = service.findByLocation(location);
		if (!dtoList.isEmpty()) {
			log.info("dto list: " + dtoList);
			model.addAttribute("list", dtoList);
			model.addAttribute("size", "No. of records: " + dtoList.size());
			return "AdminViewData";
		} else {
			model.addAttribute("error", "No record found");
			return "AdminViewData";
		}
	}
	
	@RequestMapping("userParkingInfo")
	public String findUserParkingInfo(Model model, HttpServletRequest req) {
		log.info("running findUserParkingInfo()");
	List<UserParkingDTO> list = service.findAll();
	log.info("List<UserParkingDTO> list: "+list);
	if(list!=null) {
		HttpSession session = req.getSession();
		session.setAttribute("upList", list);
	}else {
		model.addAttribute("error", "No record fond");
	}
	return "ViewUserParkingInfo";
	}
	
	//user
	@PostMapping("userRegistration")
	public String userRegistration(UserDTO userDto, UserParkingDTO upDto, Model model, MultipartFile file) throws FileNotFoundException, IOException {
		log.info("running userRegistration");
		
		upDto.setFileName(System.currentTimeMillis()+"_"+file.getOriginalFilename());
		upDto.setOriginalFileName(file.getOriginalFilename());
		upDto.setContentType(file.getContentType());
		
		File physicalFile = new File(FileConstant.FILE_LOCATION + upDto.getFileName());
		try (OutputStream os = new FileOutputStream(physicalFile)) {
			os.write(file.getBytes());
		}
		
		boolean status = service.validateAndRegister(userDto, upDto);
		if (status) {
			model.addAttribute("success", "Registration done Successfully");
		} else {
			model.addAttribute("error", "Email is already registered, please login");
		}
		return "UserRegistration";
	}

	@PostMapping("generateOTPAndLogin")
	public String onGenerateOTPAndLogin(String email, String generateOtp, Integer otp, String login, Model model,
			HttpServletRequest req) throws IOException {

		log.info("running onGenerateOTPAndLogin()");
		log.info("Controller: generateOtp: " + generateOtp);
		log.info("controller: login from UI: " + login);
		log.info("controller: email from UI: " + email);
		log.info("controller: otp from UI: " + otp);

		if (generateOtp != null && "Generate OTP".equals(generateOtp)) {

			UserDTO dto = service.findByUserEmail(email);

			HttpSession session = req.getSession();
			session.setAttribute("emailId", email);

			if (dto != null) {
				boolean otpStatus = service.generateOtp(email);
				if (otpStatus) {
					model.addAttribute("mail", email);
					model.addAttribute("generateOtpSuccess",
							"OTP sent to registered mail-id, will be expired after 2 mins");
				} else {
					model.addAttribute("mail", "");
					model.addAttribute("generateOtpError", "Re-generate the OTP");
				}
			} else {
				model.addAttribute("mail", email);
				model.addAttribute("emailError", "*Not a registered mail-id");
			}
		} else if (login != null && "Login".equals(login)) {
			log.info("validating otp...");
			log.info("controller: onGenerateOTP(): email from login.jsp: " + email);

			if (email != null && email != "") {
				UserDTO dto = service.findByUserEmail(email);
				boolean otpNotExpired = dto.getOtpExpiryTime().isAfter(LocalTime.now());
				log.info("controller: onGenerateOTP(): dto.getOtpExpiryTime():" + dto.getOtpExpiryTime());
				log.info("controller: onGenerateOTP(): LocalTime.now():" + LocalTime.now());
				log.info("Is OTP active? " + otpNotExpired);
				if (otpNotExpired) {
					log.info("OTP not expired");
					boolean isOtpValid = service.validateOTP(email, otp);  //validateOTP(email, otp);
					UserDTO recentDto = service.findByUserEmail(email);
					if (isOtpValid) {
						log.info("controller: onGenerateOTP():  valid otp");
						HttpSession session = req.getSession();
						session.setAttribute("userDto", recentDto);
						Map<String, Long> dueDetails = service.findPamentDueDays(email);
					//	session.setAttribute("dueDays", dueDetails);
						if (dueDetails!=null) {
							log.info("dueDetails are not empty");
							model.addAttribute("due", "Payment Due");
							model.addAttribute("dueDays", dueDetails);
						} else {
							model.addAttribute("noDue", "No due");
						}
						return "UserLoginSuccess";
					} else {
						log.info("controller: onGenerateOTP():  in-valid otp");
						HttpSession session = req.getSession();
						String mail = (String) session.getAttribute("emailId");

						if (recentDto.getOtpCount() < 3) {
							log.info("controller: onGenerateOTP(): " + recentDto.getOtpCount() + " < 3");
							model.addAttribute("mail", mail);
							model.addAttribute("otpError", "*Invalid OTP");
							return "UserLogin";
						} else {
							log.info("controller: onGenerateOTP(): " + recentDto.getOtpCount() + " = 3");
							model.addAttribute("mail", "");
							model.addAttribute("acctLocked", "*Your account is blocked");
							return "UserLogin";
						}
					}
				} else {
					log.info(LocalTime.now() + "(now)" + " = " + dto.getOtpExpiryTime() + "(otp expiry time)");
					log.info("OTP expired");
					model.addAttribute("otpExpired", "*OTP expired, please generate the new OTP");
					return "UserLogin";
				}
			}
			log.info("email is null");
			model.addAttribute("generateOtpError", "*Invalid data, please generate OTP");
			return "UserLogin";
		}
		return "UserLogin";
	}

	// add user parking info
	@RequestMapping("addUserParkingInfo")
	public String onAddUserParkingInfo(UserParkingDTO upDto, Model model, MultipartFile file, HttpServletRequest req) throws FileNotFoundException, IOException {
		log.info("controller: running onAddUserParkingInfo()");
		UserParkingDTO dto = service.findByVehicleNo(upDto.getVehicleNo());
		if (dto == null || !dto.isActive()) {
			log.info("controller: onAddUserParkingInfo(): new vehicle");
			
			upDto.setFileName(System.currentTimeMillis()+"_"+file.getOriginalFilename());
			upDto.setOriginalFileName(file.getOriginalFilename());
			upDto.setContentType(file.getContentType());
			
			File physicalFile = new File(FileConstant.FILE_LOCATION + upDto.getFileName());
			try (OutputStream os = new FileOutputStream(physicalFile)) {
				os.write(file.getBytes());
			}
			HttpSession session = req.getSession();
			Object mail = session.getAttribute("emailId");
			log.info("controller: onAddUserParkingInfo(): mailid of line#133: "+mail);
			service.addUserParkingInfo(upDto, (String)mail);
			model.addAttribute("success", "Parking slot booked!");
			return "UserParkingInfo";
		}
		log.info("controller: onAddUserParkingInfo(): This vehicle is already parked");
		model.addAttribute("error", "*This vehicle is already parked");
		return "UserParkingInfo";
	}

	// view user data
	@GetMapping("userViewData")
	public String onUserViewData(Model model, HttpServletRequest req) {
		log.info("onUserViewData()");
		
		HttpSession session = req.getSession();
		String mail = (String) session.getAttribute("emailId");
		Object userDto = session.getAttribute("userDto");
		
		List<UserParkingDTO> upDtos = service.findAllById(mail);

		if (userDto != null && !upDtos.isEmpty()) {
			model.addAttribute("personalData", userDto);
			model.addAttribute("parkingData", upDtos);
			return "UserViewData";
		}
		model.addAttribute("error", "Facing some issue, please retry after some time");
		return "UserViewData";
	}
	
	
	@GetMapping("userParkingData")
	public String viewUserParkingData(String location, Model model, HttpServletRequest req) {
		log.info("viewUserParkingData()");
		
		HttpSession session = req.getSession();
		String mail = (String) session.getAttribute("emailId");
		Object userDto = session.getAttribute("userDto");
		
		List<UserParkingDTO> upDtos = service.findAllById(mail);

		if (userDto != null && !upDtos.isEmpty()) {
			model.addAttribute("personalData", userDto);
			model.addAttribute("parkingData", upDtos);
			return "UserViewData";
		}
		model.addAttribute("error", "Facing some issue, please retry after some time");
		return "UserViewData";
	}
	
	
	@GetMapping("showFile")
	public void showFile(String fileName, String contentType, HttpServletResponse resp) throws IOException {
		log.info("running showFile()");
		File physicalFile = new File(FileConstant.FILE_LOCATION + fileName); // STEP 1: GET ref of the file, by
																					// name passed
		resp.setContentType(contentType); // STEP2 : set content type
		OutputStream outputStream = resp.getOutputStream(); // STEP 3: write file as Bytes to response
		FileInputStream inputStream = new FileInputStream(physicalFile);
		byte[] buffer = new byte[4096];
		int length;
		while ((length = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, length);
		}
		inputStream.close();
		outputStream.flush();
	}
	
	//	@RequestMapping(value ="/parkinginfo/byVehicleNo/{vehicleNo}") 
		@GetMapping("/parkinginfo")
		public String loadingUserParkingData(String vehicleNo, Model model, MultipartFile file, HttpServletRequest req) {
	//	public RedirectView loadingUserParkingData(@PathVariable String vehicleNo, Model model, MultipartFile file, HttpServletRequest req) {
		log.info("running loadingUserParkingData()");
		log.info("controller: loadingUserParkingData(): vehicleNo: "+vehicleNo);
		UserParkingDTO upDto = service.findByVehicleNo(vehicleNo);
		log.info("controller: loadingUserParkingData(): upDto: "+upDto);
			model.addAttribute("upDto", upDto);
			log.info("req.getContextPath(): "+req.getContextPath());
		//	RedirectView redirectView=new RedirectView();
		//	redirectView.setUrl(req.getContextPath()+ "/UpdateUserParkingInfo.jsp");
			return "UpdateUserParkingInfo";
		//	return redirectView;
	}
	
	@PostMapping("parkingInfo")
	public String updateUserParkingData(UserParkingDTO upDto, Model model, MultipartFile file, HttpServletRequest req)
			throws FileNotFoundException, IOException {
		log.info("controller: running updateUserParkingData()");
		log.info("controller: running updateUserParkingData(): upDto: "+upDto);
		log.info("controller: running updateUserParkingData(): file.isEmpty(): "+file.isEmpty());
		log.info("controller: running updateUserParkingData(): OriginalFileName: "+file.getOriginalFilename());
		
		if (!file.isEmpty()) {
			log.info("controller: updateUserParkingData(): new pic uploaded");
			upDto.setFileName(System.currentTimeMillis() + "_" + file.getOriginalFilename());
			upDto.setOriginalFileName(file.getOriginalFilename());
			upDto.setContentType(file.getContentType());
			
			File physicalFile = new File(FileConstant.FILE_LOCATION + upDto.getFileName());
			try (OutputStream os = new FileOutputStream(physicalFile)) {
				os.write(file.getBytes());
			}
		}
		log.info("controller: updateUserParkingData(): upDto after adding new pic: "+upDto);
		boolean updated = service.updateUserParkingInfo(upDto);
		if (updated) {
			log.info("controller: updateUserParkingData(): data updated successfully!");
			model.addAttribute("success", "Data updated successfully!");
			return "UpdateUserParkingInfo";
		} else {
			log.info("controller: updateUserParkingData(): data not updated");
			model.addAttribute("error", "*Data not updated");
			return "UpdateUserParkingInfo";
		}
	}
	
//	@GetMapping("/payment/{email}")
	@GetMapping("paymentDue")
	public String loadPaymentDueData(String email, Model model, HttpServletRequest req) {
		log.info("running onPayment()");

		List<UserParkingDTO> upList = service.findAllById(email);
		
		List<UserParkingDTO> paymentPendingList = upList.stream().filter(e -> e.isActive())
				.filter(e -> e.getPayment().equals("pending")).collect(Collectors.toList());
		log.info("onPayment(): paymentPendingList: " + paymentPendingList);
		if(!paymentPendingList.isEmpty()) {
			model.addAttribute("ppList", paymentPendingList);
			model.addAttribute("due", "Payment due:");
			return "Payment";
		}
		model.addAttribute("noDue", "no payment due");
		return "Payment";
	}
	
	@GetMapping("payment")
	public String onPayment(String vehicleNo, Model model, HttpServletRequest req) {
		log.info("running onPayment()");

		boolean updated = service.updatePayment(vehicleNo);

		if (updated) {
			log.info("onPayment(): amount paid");
			HttpSession session = req.getSession();
			String mail = (String) session.getAttribute("emailId");

			List<UserParkingDTO> upList = service.findAllById(mail);
			List<UserParkingDTO> paymentPendingList = upList.stream().filter(e -> e.isActive())
					.filter(e -> e.getPayment().equals("pending")).collect(Collectors.toList());
			log.info("onPayment(): paymentPendingList: " + paymentPendingList);
			
			if (!paymentPendingList.isEmpty()) {
				log.info("onPayment(): paymentPendingList is not empty");
				model.addAttribute("ppList", paymentPendingList);
				model.addAttribute("paid", "Payment done: "+vehicleNo);
				model.addAttribute("due", "Payment due:");
				return "Payment";
			}
			model.addAttribute("paid", "Payment done: "+vehicleNo);
			model.addAttribute("noDue", "No payment due");
			return "Payment";
		}
		model.addAttribute("error", "not paid");
		return "Payment";
	}
	
	@GetMapping("duePayment")
	public String loadDueDays(String email, Model model) {
		log.info("running loadDueDays()");	
		Map<String, Long> dueDetails = service.findPamentDueDays(email);
		if (dueDetails!=null) {
		log.info("dueDetails are not empty");
		model.addAttribute("due", "Payment Due");
		model.addAttribute("dueDays", dueDetails);
	} else {
		model.addAttribute("noDue", "No due");
	}
	return "UserLoginSuccess";
}
	
	@RequestMapping("deleteUserParkingData")
	public String onDeleteUserParkingData(String vehicleNo, Model model, HttpServletRequest req) {
		log.info("running onDeleteUserParkingData()");
		boolean status=service.deleteUserParkingEntityByVehicleNo(vehicleNo);
		if(status) {
			
			HttpSession session = req.getSession();
			String mail = (String) session.getAttribute("emailId");
			
			UserDTO userDto = service.findByUserEmail(mail);
			List<UserParkingDTO> upDtos = service.findAllById(mail);

			if (userDto != null && !upDtos.isEmpty()) {
				model.addAttribute("personalData", userDto);
				model.addAttribute("parkingData", upDtos);
				model.addAttribute("success", "record deleted");
				log.info("record deleted by vehicleNo");
		}
		}else {
			model.addAttribute("error", "Unable to delete the record, please retry");
			log.info("record not deleted by vehicleNo");
		}
		return "UserViewData";
	}
	
	@PreDestroy
	public void destroy() {
		log.info("application is closed");
	}
	
}
