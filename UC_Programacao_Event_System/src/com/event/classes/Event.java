package com.event.classes;

import java.time.LocalDateTime;

public class Event {
	
	private String name;
	private String address;
	private String description;
	private LocalDateTime date;
	private boolean confirmated;
	private String category;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public boolean isConfirmated() {
		return confirmated;
	}
	public void setConfirmated(boolean confirmated) {
		this.confirmated = confirmated;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

}
