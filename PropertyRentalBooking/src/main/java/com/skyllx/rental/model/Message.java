package com.skyllx.rental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String senderUsername;
    private String receiverUsername;
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSenderUsername() {
		return senderUsername;
	}
	public void setSenderUsername(String senderUsername) {
		this.senderUsername = senderUsername;
	}
	public String getReceiverUsername() {
		return receiverUsername;
	}
	public void setReceiverUsername(String receiverUsername) {
		this.receiverUsername = receiverUsername;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	private String text;

    // Getters & Setters
}
