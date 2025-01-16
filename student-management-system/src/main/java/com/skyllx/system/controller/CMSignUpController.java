package com.skyllx.system.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.skyllx.system.dto.UserDTO;
import com.skyllx.system.service.CMSignUpService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
@EnableWebMvc
public class CMSignUpController {

	@Autowired
	private CMSignUpService service;

	public CMSignUpController() {
		log.info("Created " + this.getClass());
	}
	
	@GetMapping(value = "/logo", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> logo() throws IOException {
		ClassPathResource imgFile = new ClassPathResource("resources/static/images/xworkz_logo.png");
		byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytes);
	}

	@GetMapping("/register")
	public String onSave() {
		log.info("@GetMapping onSave in CMSignUpController");
		return "SignUp";
	}

	@PostMapping("/register")
	public String onSave(UserDTO registerdto, Model model,BindingResult result) {
		log.info("@PostMapping onSave in CMSignUpController" + registerdto);
		if(result.hasErrors()) {
			System.out.println("Binding result violation");
			result.getAllErrors().forEach(e->System.out.println(e.getDefaultMessage()));
            return "SignUp";
        }
		log.info("this.service : "+this.service);
		registerdto.setCreatedBy(registerdto.getUserId());
		registerdto.setCreatedOn(LocalDateTime.now());
		Set<ConstraintViolation<UserDTO>> violations = this.service.validateAndSave(registerdto);
		if (violations.isEmpty()) {
			log.info("No violations in dto, going to save in database" + registerdto);
			
			// To send mail
			boolean sendMail = this.service.sendEmail(registerdto);
			log.info("sendMail : "+sendMail);
			
			registerdto.setPassword(null);
			model.addAttribute("dto", registerdto);
			model.addAttribute("signUpSuccess", "Successfully Registered, you can sign in now");
			if(sendMail) {
			model.addAttribute("signUpEmailConfirmation", "A confirmation mail is send to : "+registerdto.getEmail());
			}
			return "index";
		}
		log.info("Violations in onSave, the dto  : " + registerdto);
		for (ConstraintViolation<UserDTO> violation : violations) {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            log.info(fieldName+" - "+errorMessage);
		}
		model.addAttribute("dto", registerdto);
		model.addAttribute("errors", violations);
		return "SignUp";
	}

}
