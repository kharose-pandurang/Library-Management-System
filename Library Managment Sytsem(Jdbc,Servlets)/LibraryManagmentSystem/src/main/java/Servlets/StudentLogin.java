package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Student;
import DTO.Database;

public class StudentLogin extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Database d1=new Database();
		String sid=req.getParameter("sid");
		String smail=req.getParameter("smail");
		try {
			Student student=d1.getStudent(sid);
			if(student.getSmail().equals(smail)) {
				
				System.out.println("doone");
				RequestDispatcher dispatcher = req.getRequestDispatcher("userpage.jsp");
				req.setAttribute("student", student);
				req.setAttribute("msg", "yes");
				dispatcher.include(req, resp);
			}
			else {
				RequestDispatcher dispatcher = req.getRequestDispatcher("uloginpage.jsp");
				req.setAttribute("msg", "no");
				dispatcher.include(req, resp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
