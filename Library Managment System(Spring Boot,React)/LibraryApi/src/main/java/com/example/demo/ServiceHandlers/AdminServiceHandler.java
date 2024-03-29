package com.example.demo.ServiceHandlers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.BookReqService;
import com.example.demo.Service.BookService;
import com.example.demo.Service.IssuedBookService;
import com.example.demo.dao.Book;
import com.example.demo.dao.BookReq;
import com.example.demo.dao.IssuedBooks;
import com.example.demo.dao.Msg;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminServiceHandler {
	
	@Autowired
	BookService service1;
	@Autowired
	IssuedBookService service2;
	
	@Autowired
	BookReqService service3;
	
	
	@PostMapping("/addBook")
	public ResponseEntity<String> AddBooks(@RequestBody Book book) {

		if(book.getBname()!=null && service1.existsById(book.getBname())) {
			Book oldBook=service1.getById(book.getBname());
			int count=oldBook.getCount()+book.getCount();
			oldBook.setCount(count);
			service1.save(oldBook);
			return ResponseEntity.of(Optional.of("Update"));
		}else if (book.getBname()!=null) {
			service1.save(book);
			return ResponseEntity.of(Optional.of("Books Added Successfully!"));
		}else {
			System.out.println("error");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	@GetMapping("/getAllBooks")
	public List<Book> getAllBooks(){ 
		
		return service1.findAll();
	}
	
	@PostMapping("/deletebook")
	public ResponseEntity<String> deleteBook(@RequestBody Book book){

		if(service1.existsById(book.getBname())) {
			Book oldBook=service1.getById(book.getBname());
			service1.delete(oldBook);
			return ResponseEntity.of(Optional.of("Deleted"));
		}else {
			System.out.println("error");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	@PostMapping("/updatebook")
	public ResponseEntity<String> updateBooks(@RequestBody Book book) {
		
		if(book.getBname()!=null && service1.existsById(book.getBname())) {
			Book oldBook=service1.getById(book.getBname());
			oldBook.setAname(book.getAname());
			oldBook.setCount(book.getCount());
			oldBook.setSub(book.getSub());
			service1.save(oldBook);
			return ResponseEntity.of(Optional.of("Update"));
		}else {
			System.out.println("error");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("/getAllIssuedBooks")
	public List<IssuedBooks> getAllIssuedBooks(){
		System.out.println("fetching all issued books");
		return service2.findAll();
	}
	
	@PostMapping("/verifybookreq")
	public ResponseEntity<Msg> verifyBookreq(@RequestBody BookReq req){
		if (req.getSrno()!=0 && service3.existsById(req.getSrno())) {
			BookReq oldReq=service3.getById(req.getSrno());
			IssuedBooks newissue=new IssuedBooks();
			newissue.setSid(oldReq.getSid());
			newissue.setBname(oldReq.getBname());
			newissue.setSname(oldReq.getSname());
			service2.save(newissue);
			Book oldBook=service1.getById(oldReq.getBname());
			oldBook.setCount(oldBook.getCount()-1);
			service1.save(oldBook);
			service3.delete(oldReq);
			return ResponseEntity.of(Optional.of(new Msg("Verified Successfully!")));
		}else {
			return ResponseEntity.of(Optional.of(new Msg("Request Not found")));
		}
	}
	
	@GetMapping("/getallreq")
	public List<BookReq> getAllReq(){
		System.out.println("fetching all req");
		return service3.findAll();
	}

}
