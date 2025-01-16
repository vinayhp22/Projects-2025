package com.skyllx.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.skyllx.system.dto.UserDTO;
import com.skyllx.system.service.CMSignInService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class CMSignInController {

	@Autowired
	private CMSignInService service;

	public CMSignInController() {
		log.info("Created " + this.getClass());
	}

	@GetMapping("/signIn")
	public String onSignIn() {
		log.info("onSignIn() getMapping");
		return "SignIn";
	}

	@PostMapping("/signIn")
	public String onSignIn(String userId, String password, Model model, HttpServletRequest request) {
		log.info("onSignIn() postMapping");
		UserDTO user = service.findByUserId(userId);
		if (user != null) {
			log.info("Attempts : " + user.getAttempts() + " Locked : " + user.isLocked());
		}
		if (user != null && user.getAttempts() < 3 && !user.isLocked()) {
			if (service.authenticateUser(userId, password)) {
				// correct credentials
				HttpSession session = request.getSession();

				session.setAttribute("dto", user);

				model.addAttribute("dto", user);
				model.addAttribute("sign_in_success", "Successfully logged in, email is : " + user.getEmail());
				if (user.getAttempts() != 0) {
					service.updateAttempts(userId, 0);
				}
				return "SignInSuccessPage";
			} else {
				// wrong password
				int attempts = user.getAttempts() + 1;
				if (attempts == 3) {
					// account locked
					service.updateLock(userId);
					model.addAttribute("errors", "Your account has been locked. Please contact the administrator.");
					return "SignIn";
				}
				service.updateAttempts(userId, attempts);
				model.addAttribute("errors", "Invalid password. You have " + (3 - attempts) + " attempts left.");
			}
		} else if (user != null && user.getAttempts() >= 3) {
			model.addAttribute("errors", "Your account has been locked. Please contact the administrator.");
		} else {
			// user not found
			model.addAttribute("errors", "Invalid username.");
		}
		return "SignIn";
	}

	@GetMapping("/resetPassword")
	public String resetPassword() {
		log.info("resetPassword() - @GetMapping");
		return "PasswordReset";
	}

	@PostMapping("/resetPassword")
	public String resetPassword(String email, Model model) {
		log.info("resetPassword() - @PostMapping");
		if (email == "") {
			log.info("email is blank");
			model.addAttribute("errors", "Please enter a email id");
			return "PasswordReset";
		}
		if (this.service.checkEmail(email)) {
			// if mail is present, getting dto by mail
			UserDTO dto = this.service.findByEmail(email);
			log.info("mail is present, getting dto : " + dto);
			if (dto != null) {
				// sending the reset mail and returning confirmation
				boolean sendResetMail = this.service.sendResetMail(dto);
				if (sendResetMail) {
					model.addAttribute("success",
							"Reset mail send successfully to " + dto.getEmail() + ", please login");
				} else {
					model.addAttribute("mailReject", "Password reset mail not send, please try after sometime");
				}
				return "UpdatePassword";
			}
		} else {
			model.addAttribute("errors", "Email id :" + email + " is not correct, please try another valid email");
		}
		return "PasswordReset";
	}

	@PostMapping("/updatePassword")
	public String updatePassword(UserDTO dto, String newPassword, String confirmNewPassword, Model model) {
		log.info("Running UpdatePassword");
		if (dto != null) {
			if (service.authenticateUpdate(dto.getUserId(), dto.getPassword())) {
				if (this.service.compareNewPassword(newPassword, confirmNewPassword)) {

					if (this.service.updatePassword(dto, newPassword)) {
						log.info("Password updated successfully, plese login with new password");
						model.addAttribute("updatePasswordSuccess",
								"Password updated successfully, plese login with new password");
						return "SignIn";
					} else {
						log.info("Password update not successfull");
						model.addAttribute("error", "Password update not successfull");
					}
				} else {
					log.info("newPassword and  confirmNewPassword password doest not match");
					model.addAttribute("error", "newPassword and  confirmNewPassword password doest not match");
				}
			}
			log.info("invalid password, please check the mail for correct password");
			model.addAttribute("error", "invalid password, please check the mail for correct password");
		}
		return "UpdatePassword";
	}
}
