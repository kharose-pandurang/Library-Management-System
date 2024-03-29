package Servlets;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Database;

public class StudentBookReturnReq extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid=req.getParameter("sid");
		String bname=req.getParameter("bname");
		Database database=new Database();
		try {
			if(database.StudentRequestReturn(sid, bname)) {
				ResultSet newList=database.getStudentIssues(sid);
				RequestDispatcher dispatcher=req.getRequestDispatcher("AllIssuedBooksAndReturn.jsp");
				req.setAttribute("Ilist",newList);
				req.setAttribute("msg","yes");
				dispatcher.include(req, resp);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
