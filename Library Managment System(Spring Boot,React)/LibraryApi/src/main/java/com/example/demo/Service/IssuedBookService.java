package com.example.demo.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dao.IssuedBooks;

public interface IssuedBookService extends JpaRepository<IssuedBooks, Integer> {
	
	public IssuedBooks getById(int id);

}
