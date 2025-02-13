package com.skyllx.rental.controller;

import com.skyllx.rental.model.Property;
import com.skyllx.rental.dao.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/properties")
public class ImageController {
    @Autowired
    private PropertyRepository propertyRepository;

    @GetMapping("/image")
    public ResponseEntity<byte[]> getPropertyImage(@RequestParam("id") Long propertyId) {
        Optional<Property> property = propertyRepository.findById(propertyId);
        
        if (property.isPresent() && property.get().getImage() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "image/jpeg");

            return new ResponseEntity<>(property.get().getImage(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
