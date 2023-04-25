package com.example.demo.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EventRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.model.Event;
import com.example.demo.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.ArrayList;

@Service
public class EventServices {
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private UserRepository userRepository;
	
	public Event addEvent(Event event) {
		Event temp=this.eventRepository.findById(event.getId());
		if(temp==null) {
		Event result=this.eventRepository.save(event);
		return result;
		}
		return temp;
	}
	public List<Event> getAllEvents() throws ParseException{
		List<Event> list=(List<Event>)this.eventRepository.findAll();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    Date date = new Date(); 
	    List<Event> myList = new ArrayList<>();
	    int i;
	    for(i=0;i<list.size();i++) {
	    	Event event=list.get(i);
	    	Date d1=formatter.parse(event.getStartDate());
	    	Date d2=formatter.parse(event.getEndDate());
	    	if(d1.compareTo(date)>=0 && date.compareTo(d2)<=0)
	    		event.setStatus("In Progress");
	    	else if(d1.compareTo(date)<0)
	    		event.setStatus("Open");
	    	else if(date.compareTo(d2)>0)
	    		event.setStatus("Completed");
	    	myList.add(event);
	    }
	    this.eventRepository.saveAll(myList);
		return myList;
	}
	public List<Event> getEventByLocation(String location) throws ParseException{
		List<Event> list=(List<Event>)this.eventRepository.findByLocation(location);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    Date date = new Date(); 
	    List<Event> myList = new ArrayList<>();
	    int i;
	    for(i=0;i<list.size();i++) {
	    	Event event=list.get(i);
	    	Date d1=formatter.parse(event.getStartDate());
	    	Date d2=formatter.parse(event.getEndDate());
	    	if(d1.compareTo(date)>=0 && date.compareTo(d2)<=0)
	    		event.setStatus("In Progress");
	    	else if(d1.compareTo(date)<0)
	    		event.setStatus("Open");
	    	else if(date.compareTo(d2)>0)
	    		event.setStatus("Completed");
	    	myList.add(event);
	    }
	    this.eventRepository.saveAll(myList);
		return myList;
	}
	public Event getEventById(long id) throws ParseException {
		Event event=this.eventRepository.findById(id);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    Date date = new Date(); 
	    Date d1=formatter.parse(event.getStartDate());
    	Date d2=formatter.parse(event.getEndDate());
    	if(d1.compareTo(date)>=0 && date.compareTo(d2)<=0)
    		event.setStatus("In Progress");
    	else if(d1.compareTo(date)<0)
    		event.setStatus("Open");
    	else if(date.compareTo(d2)>0)
    		event.setStatus("Completed");
    	this.eventRepository.save(event);
		return event;
	}
	public Event updateEvent(Event event,long id) {
		Event temp=this.eventRepository.findById(id);
		if(temp!=null) {
			Set<User> user=temp.getUsers();
			user.addAll(event.getUsers());
			event.setUsers(user);
		    return this.eventRepository.save(event);
		}
		return temp;
	}
	public void deleteEventById(long id) {
		Event temp=this.eventRepository.findById(id);
		if(temp!=null)
		this.eventRepository.deleteById(id);
	}
	public List<User> getAllUsers(){
		List<User> list=(List<User>) this.userRepository.findAll();
		return list;
	}
	public User findUserById(long id) {
		User user=this.userRepository.findUserById(id);
		return user;
	}
	public void deleteUserById(long id) {
		User temp=this.userRepository.findUserById(id);
		if(temp!=null)
		this.userRepository.deleteById(id);
	}
}
