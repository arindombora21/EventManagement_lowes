package com.example.demo.Controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Event;
import com.example.demo.model.User;
import com.example.demo.services.EventServices;

@RestController
public class EventController {
	@Autowired
	private EventServices eventServices;
	
	@GetMapping("/events")
	public List<Event> getAllEvents() throws ParseException{
		return this.eventServices.getAllEvents();
	}
	@PostMapping("/events")
	public Event addEvent(@RequestBody Event event) throws ParseException{
		return this.eventServices.addEvent(event);
	}
	@GetMapping("/events/{location}")
	public List<Event> getEventsByLocation(@PathVariable("location") String location) throws ParseException{
		return this.eventServices.getEventByLocation(location);
	}
	@GetMapping("/event/{id}")
	public Event getEvent(@PathVariable("id") long id) throws ParseException{
		return this.eventServices.getEventById(id);
	}
	@DeleteMapping("/events/{id}")
	public void deleteEvent(@PathVariable("id") long id) {
		this.eventServices.deleteEventById(id);
	}
	@PutMapping("/events/{id}")
	public Event updateEvent(@RequestBody Event event,@PathVariable("id") long id) {
		return this.eventServices.updateEvent(event,id);
	}
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") long id) throws ParseException{
		return this.eventServices.findUserById(id);
	}
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable("id") long id) {
		this.eventServices.deleteUserById(id);
	}
}
