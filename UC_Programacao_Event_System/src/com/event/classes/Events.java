package com.event.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Events implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Event> events = new ArrayList<Event>();

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
}
