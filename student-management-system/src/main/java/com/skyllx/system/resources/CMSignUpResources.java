package com.skyllx.system.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.skyllx.system.service.CMSignUpService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@Slf4j
public class CMSignUpResources {

	@Autowired
	private CMSignUpService service;

	public CMSignUpResources() {
		log.info("Created :" + this.getClass());
	}



	@GetMapping(value = "/userId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String checkUser(@PathVariable String userId) {
		log.info("checkUser(String userId) " + userId);
		Long countByUserId = this.service.countByUserId(userId);
		log.error("" + countByUserId);
		if (countByUserId == 0) {
			log.info("Not found similar userId in db");
			return "";
		} else {
			log.error("Found similar userId in db, try another..");
			return "User Id is exist, try another..";
		}
	}

	@GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String checkEmail(@PathVariable String email) {
		log.info("checkEmail(String email) " + email);
		Long countByEmail = this.service.countByEmail(email);
		log.error("" + countByEmail);
		if (countByEmail == 0) {
			log.info("Not found similar email in db");
			return "";
		} else {
			log.error("Found similar email in db, try another..");
			return "Email is exist, try another..";
		}
	}

	@GetMapping(value = "/mobile/{mobile}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String checkMobile(@PathVariable Long mobile) {
		log.info("checkMobile(String mobile) " + mobile);
		Long countByMobile = this.service.countByMobile(mobile);
		log.error("" + countByMobile);
		if (countByMobile == 0) {
			log.info("Not found similar mobile in db");
			return "";
		} else {
			log.error("Found similar mobile in db, try another..");
			return "Mobile is exist, try another..";
		}
	}

}
