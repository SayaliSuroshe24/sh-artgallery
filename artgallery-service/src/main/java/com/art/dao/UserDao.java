package com.art.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.art.pojos.User;

public interface UserDao extends JpaRepository<User, Long> {
	//User findByEmail(String email);
}
