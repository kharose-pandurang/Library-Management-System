package com.example.demo.ServiceHandlers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.BookReqService;
import com.example.demo.Service.BookService;
import com.example.demo.Service.IssuedBookService;
import com.example.demo.Service.StudentService;
import com.example.demo.dao.Book;
import com.example.demo.dao.BookReq;
import com.example.demo.dao.IssuedBooks;
import com.example.demo.dao.Msg;
import com.example.demo.dao.Student;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class StudentServiceHandler {
	@Autowired
	StudentService service1;
	@Autowired
	BookService service2;
	@Autowired
	BookReqService service3;
	@Autowired
	IssuedBookService service4;
	
	@PostMapping("/signup")
	public ResponseEntity<Student> studentSignup(@RequestBody Student student){
	
		if(student.getSname()!=null && !service1.existsById(student.getSid())) {
			Student s=new Student();
			s.setSname("done");
			service1.save(student);
			return ResponseEntity.of(Optional.of(s));
		}else if (student.getSname()!=null && service1.existsById(student.getSid())) {
			return ResponseEntity.of(Optional.of(student));
		}else {
			System.out.println("error");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("/allstudents")
	public List<Student> getAllStudents(){
		return service1.findAll();
	}
	
	@PostMapping("/reqabook")
	public  ResponseEntity<Msg> requestBook(@RequestBody BookReq re){
	
		BookReq req=new BookReq();
		req.setBname(re.getBname());
		req.setSid(re.getSid());
		req.setSname(re.getSname());
		
		if(service2.existsById(req.getBname()) && service2.getById(req.getBname()).getCount()>0) {
			service3.save(req);
			return ResponseEntity.of(Optional.of(new Msg("Request Sent Successfully")));
		}else if(service2.existsById(req.getBname()) && service2.getById(req.getBname()).getCount()<=0){
			return ResponseEntity.of(Optional.of(new Msg("Failed!! Book Not Available Currently")));
		}else { 

			return ResponseEntity.of(Optional.of(new Msg("404 Book Not Found!")));
		}
	}
	
	@PostMapping("/return")
	public ResponseEntity<Msg> returnBook(@RequestBody IssuedBooks book){
		if(service4.existsById(book.getSrno()) ) {
			IssuedBooks old=service4.getById(book.getSrno());
			if(service2.existsById(old.getBname())) {
				Book oldBook=service2.getById(old.getBname());
				oldBook.setCount(oldBook.getCount()+1);
				service2.save(oldBook);
				service4.delete(old);
				return ResponseEntity.of(Optional.of(new Msg("Book Returned Successfully!")));
			}
			else {
				return ResponseEntity.of(Optional.of(new Msg("No such Book Found!")));
			}
		}else {
			return ResponseEntity.of(Optional.of(new Msg("Error! Somthing went wrong")));
		}
	}
	
	@PostMapping("/getmyissues")
	public ResponseEntity<List<IssuedBooks>> getMyIssues(@RequestBody Student s){
		
		List<IssuedBooks> list= service4.findAll().stream().filter(temp -> temp.getSid().equals(s.getSid())).collect(Collectors.toList());
		return ResponseEntity.of(Optional.of(list));
	}

}
