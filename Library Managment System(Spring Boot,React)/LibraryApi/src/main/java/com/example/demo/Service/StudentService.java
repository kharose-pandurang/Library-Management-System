package com.example.demo.Service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dao.Student;

public interface StudentService extends JpaRepository<Student, String> {

}
