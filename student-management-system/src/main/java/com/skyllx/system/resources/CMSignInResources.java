package com.skyllx.system.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.skyllx.system.service.CMSignInService;
import com.skyllx.system.service.CMSignUpService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@Slf4j
public class CMSignInResources {

	@Autowired
	private CMSignUpService signUpService;
	
	@Autowired
	private CMSignInService signInService;

	public CMSignInResources() {
		log.info("Created :" + this.getClass());
	}

	@GetMapping(value = "/signinuserid/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String checkUser(@PathVariable String userId) {
		log.info("checkUser(String userId) " +userId);
		Long countByUserId = this.signUpService.countByUserId(userId);
		log.error(""+countByUserId);
		if(countByUserId==0) {
			log.error("Not found similar userId in db, try another..");
			return "User Id is not exist, try another..";
		}else {
			log.info("Found similar userId in db");
			return "";
		}
	}
	
	
	@GetMapping(value = "/signinemail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String checkEmail(@PathVariable String email) {
		log.info("checkEmail(String email) " +email);
		Long countByEmail = this.signUpService.countByEmail(email);
		log.error(""+countByEmail);
		if(countByEmail==0) {
			log.error("Not found similar email in db, try another..");
			return "Email is not exist, try another..";
		}else {
			log.info("Found similar email in db");
			return "";
		}
	}

}
