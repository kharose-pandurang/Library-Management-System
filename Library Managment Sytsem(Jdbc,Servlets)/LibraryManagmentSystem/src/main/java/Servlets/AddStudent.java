package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Student;
import DTO.Database;

public class AddStudent extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid=req.getParameter("sid");
		String sname=req.getParameter("sname");
		String smail=req.getParameter("smail");
		String sclass=req.getParameter("sclass");
		
		Database database=new Database();
		Student	s=new Student();
		s.setSid(sid);
		s.setSname(sname);
		s.setSmail(smail);
		s.setSclass(sclass);
		
		try {
			if (database.AddStudent(s)) {
				RequestDispatcher dispatcher=req.getRequestDispatcher("addstudent.jsp");
				req.setAttribute("msg","yes");
				dispatcher.include(req, resp);
			}
			else {
				RequestDispatcher dispatcher=req.getRequestDispatcher("addstudent.jsp");
				req.setAttribute("msg","no");
				dispatcher.include(req, resp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
