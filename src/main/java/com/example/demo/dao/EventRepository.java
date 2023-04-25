package com.example.demo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.example.demo.model.Event;

public interface EventRepository extends CrudRepository<Event,Long>{
	public Event findById(long id);
	public List<Event> findByLocation(String location);
	public void deleteById(long id);
}
