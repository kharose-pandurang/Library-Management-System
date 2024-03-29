package com.example.demo.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dao.Book;

public interface BookService extends JpaRepository<Book,String> {
	
	public Book getById(String bname);

}
