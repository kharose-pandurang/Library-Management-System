package com.example.demo.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dao.BookReq;

public interface BookReqService extends JpaRepository<BookReq, Integer> {
	
	public BookReq getById(int id);

}
