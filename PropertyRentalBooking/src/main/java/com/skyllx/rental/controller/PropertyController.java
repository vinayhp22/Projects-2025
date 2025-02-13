package com.skyllx.rental.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.skyllx.rental.dao.PropertyRepository;
import com.skyllx.rental.model.Property;
import com.skyllx.rental.model.User;
import com.skyllx.rental.service.PropertyService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/properties")
public class PropertyController {
	@Autowired
	private PropertyService propertyService;

	@Autowired
	private PropertyRepository propertyRepository;

	@GetMapping("/list")
	public String listProperties(Model model) {
		model.addAttribute("properties", propertyService.getAllProperties());
		return "property-list";
	}

	// Show the add property form
	@GetMapping("/add")
	public String showAddPropertyForm() {
		return "add-property";
	}

	// Handle adding a property
	@PostMapping("/add")
	public String addProperty(@RequestParam("title") String title, @RequestParam("description") String description,
			@RequestParam("price") double pricePerNight, @RequestParam("image") MultipartFile imageFile,
			HttpSession session) {
		User owner = (User) session.getAttribute("loggedInUser");
		if (owner == null) {
			return "redirect:/login.jsp";
		}

		try {
			propertyService.addProperty(title, description, pricePerNight, owner, imageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/properties/list";
	}

	@GetMapping("/property-details")
	public String propertyDetails(@RequestParam("id") Long propertyId, Model model) {
		Optional<Property> property = propertyRepository.findById(propertyId);
		if (property.isPresent()) {
			model.addAttribute("property", property.get());
			return "property-details"; // This maps to property-details.jsp
		} else {
			return "redirect:/error.jsp"; // Redirect to an error page if property not found
		}
	}

	// Load edit page
	@GetMapping("/edit")
	public String editProperty(@RequestParam("id") Long id, Model model) {
		Property property = propertyService.getPropertyById(id);
		model.addAttribute("property", property);
		return "edit-property";
	}

	// Handle updates
	@PostMapping("/update-property")
	public String updateProperty(@ModelAttribute Property property, Model model) {
		propertyService.updateProperty(property);
    	return "redirect: ../dashboard";
	}

	// Load delete page
	@GetMapping("/delete")
	public String deleteProperty(@RequestParam("id") Long id, Model model) {
		Property property = propertyService.getPropertyById(id);
		model.addAttribute("property", property);
		return "delete-property";
	}

	// Handle deletion
	@PostMapping("/remove-property")
	public String removeProperty(@RequestParam("id") Long id, Model model) {
		propertyService.deleteProperty(id);
    	return "redirect: ../dashboard";
	}

}
