package com.skyllx.system.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.skyllx.system.dto.UserDTO;
import com.skyllx.system.service.CMSignInService;
import com.skyllx.system.service.CMUpdateProfileService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class CMUpdateProfileController {

	@Autowired
	private CMSignInService signInService;

	@Autowired
	private CMUpdateProfileService service;

	private static String UPLOADED_FOLDER = "D:\\projects\\student-management-system\\FileUpload\\ProfilePictures\\";

//	/xworkz-vinayhp-project/FileUpload/ProfilePictures/
	public CMUpdateProfileController() {
		log.info("Created CMUpdateProfileController");
	}

	@GetMapping("/updateProfile")
	public String onUpdate(@RequestParam String userId, Model model) {
		log.info("onUpdate - GetMapping - CMUpdateProfileController ");
		UserDTO dto = this.signInService.findByUserId(userId);
		model.addAttribute("dto", dto);
		return "UpdateProfile";
	}

	@PostMapping("/updateProfile")
	public String onUpdate(String email, Long mobile, String userId, @RequestParam("profilePicture") MultipartFile file,
			Model model) {
		log.info("onUpdate - GetMapping - CMUpdateProfileController ");
		if (file.isEmpty()) {
			log.info("profilePic is empty, please select a file to upload");
		}
		UserDTO userByEmail = this.signInService.findByEmail(email);
		userByEmail.setMobile(mobile);
		userByEmail.setUserId(userId);
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				Path path = Paths
						.get(UPLOADED_FOLDER + userByEmail.getUserId() + "_" + System.currentTimeMillis() + ".jpg");
				String filename = path.getFileName().toString();
				Files.write(path, bytes);
				userByEmail.setProfilePic(filename);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Set<ConstraintViolation<UserDTO>> violations = service.validateAndUpdate(userByEmail);
		if (violations.isEmpty()) {
			log.info("No violations in dto, going to update in database" + userByEmail);

			// To send mail
//			boolean sendMail = this.service.sendEmail(dto);
//			log.info("sendMail : "+sendMail);

			model.addAttribute("dto", userByEmail);
			model.addAttribute("updateSuccess", "Successfully updated the profile...");
			return "SignInSuccessPage";
		}
		log.info("Violations in onSave, the dto  : " + userByEmail);
		model.addAttribute("dto", userByEmail);
		model.addAttribute("errors", violations);
		return "UpdateProfile";
	}

	@GetMapping("/downloadPic")
	public void onDownloadProfilePic(HttpServletResponse response, @RequestParam String profilePic, UserDTO dto,
			Model model, HttpServletRequest request) throws IOException {
		log.info("onDownloadProfilePic");
		try {
			Path path = Paths.get(UPLOADED_FOLDER + dto.getProfilePic());
			path.toFile();
			log.info("dto.getProfilePic() : " + dto.getProfilePic() + "profilePic : " + profilePic);
			HttpSession session = request.getSession();
			session.setAttribute("pic", dto.getProfilePic());
			model.addAttribute("pic", dto.getProfilePic());
			response.setContentType("image/jpeg");
			File file = new File("D:\\projects\\student-management-system\\FileUpload\\ProfilePictures\\" + profilePic);

			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
			ServletOutputStream out = response.getOutputStream();

			org.apache.commons.io.IOUtils.copy(in, out);
			response.flushBuffer();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
