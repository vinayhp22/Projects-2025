package com.skyllx.system.resources;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skyllx.system.dto.TechnologyListDTO;
import com.skyllx.system.service.TechnologyService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/")
public class TechnologyResources {

	@Autowired
	private TechnologyService service;

	public TechnologyResources() {
		log.info("Created :" + this.getClass());
	}

	@GetMapping(value = "/sortByName/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void sortByName(@PathVariable Integer id, Model model, HttpServletResponse response) {
		log.info("sortByName, id : " + id);
		List<TechnologyListDTO> tlDtos = this.service.listById(id);
		List<TechnologyListDTO> dtos = tlDtos.stream()
				.sorted((t1, t2) -> t1.getName().compareToIgnoreCase(t2.getName())).collect(Collectors.toList());
		dtos.forEach(s -> log.info("" + s));
		model.addAttribute("dtos", dtos);
		response.setHeader("Content-Type", "application/json");
	}

}