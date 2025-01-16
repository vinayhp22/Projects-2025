package com.skyllx.system.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.skyllx.system.dto.TechnologyListDTO;
import com.skyllx.system.dto.UserDTO;
import com.skyllx.system.service.CMSignInService;
import com.skyllx.system.service.TechnologyService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/")
@EnableWebMvc
public class TechnologyController {

	@Autowired
	private TechnologyService service;
	
	@Autowired
	private CMSignInService signInService;

	public TechnologyController() {
		log.info("Created :" + this.getClass());
	}

	@GetMapping("/addtechnology")
	public String addTechnology(Integer id, Model model) {
		log.info("@GetMapping(/addtechnology), id = " + id);
		UserDTO dto = this.signInService.findById(id);
		log.info("dto : "+dto);
		model.addAttribute("getUserId",dto.getUserId());
		model.addAttribute("id", id);
		return "AddTechnology";
	}

	@PostMapping("/addtechnology")
	public String addTechnology(TechnologyListDTO tlDto, Integer id, Model model) {
		log.info("@PostMapping(/addtechnology)" + tlDto + " id = " + id);
		tlDto.setId(id);
		UserDTO dto = this.signInService.findById(id);
		model.addAttribute("dto",dto);
		model.addAttribute("id", id);
		Set<ConstraintViolation<TechnologyListDTO>> violations = this.service.validateAndAdd(tlDto);
		if (violations.isEmpty()) {
			log.info("There is no violations can add a technology");
			model.addAttribute("tlDto", tlDto);
			model.addAttribute("addTechSuccess", "Successfully added the technology : " + tlDto.getName());
			return "AddTechnology";
		}
		log.info("Violations in the technology, can't add it");
		model.addAttribute("errors", violations);
		return "AddTechnology";
	}
	
	@GetMapping("/listtechnology")
	public String listTechnology(Integer id, Model model) {
		log.info("listTechnology "+id);
		UserDTO dto = this.signInService.findById(id);
		model.addAttribute("dto",dto);
		model.addAttribute("id", id);
		List<TechnologyListDTO> dtos = this.service.listById(id);
		
		if(dtos.isEmpty()) {
			log.info("There is no technologies added");
			model.addAttribute("emptyTechnologies","There is no technologies added");
			return "SignInSuccessPage";
		}
		log.info("dtos.size()"+dtos.size());
		model.addAttribute("dtos", dtos);
		return "TechnologiesList";
	}

}
