package com.skyllx.rental.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Property {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private double pricePerNight;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User owner;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image; // Stores the image as a byte array
    
    // Constructor
    public Property(String title, String description, double pricePerNight, User owner) {
        this.title = title;
        this.description = description;
        this.pricePerNight = pricePerNight;
        this.owner = owner;
    }
}
