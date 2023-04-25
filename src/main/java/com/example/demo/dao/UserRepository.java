package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;


public interface UserRepository extends CrudRepository<User,Long>{
	public User findUserById(long id);
	public void deleteUserById(long id);
}
