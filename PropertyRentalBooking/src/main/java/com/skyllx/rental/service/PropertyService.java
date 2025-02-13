package com.skyllx.rental.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.skyllx.rental.dao.PropertyRepository;
import com.skyllx.rental.model.Property;
import com.skyllx.rental.model.User;

@Service
public class PropertyService {
	@Autowired
	private PropertyRepository propertyRepository;

	public List<Property> getAllProperties() {
		return propertyRepository.findAll();
	}

	// Add a new property
	public void addProperty(String title, String description, double pricePerNight, User owner, MultipartFile imageFile)
			throws IOException {
		Property property = new Property();
		property.setTitle(title);
		property.setDescription(description);
		property.setPricePerNight(pricePerNight);
		property.setOwner(owner);

		// Convert image file to byte array
		if (!imageFile.isEmpty()) {
			property.setImage(imageFile.getBytes());
		}

		propertyRepository.save(property);
	}

	public Property getPropertyById(Long id) {
		Optional<Property> property = propertyRepository.findById(id);
		return property.orElse(null);
	}

	public void updateProperty(Property property) {
		propertyRepository.save(property);
	}

	public void deleteProperty(Long id) {
		propertyRepository.deleteById(id);
	}

}
