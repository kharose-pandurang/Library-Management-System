package Servlets;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Database;

public class getStudentIssues extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid=req.getParameter("sid");
		System.out.println(sid);
		Database database=new Database();
		try {
			ResultSet Ilist=database.getStudentIssues(sid);
			RequestDispatcher dispatcher=req.getRequestDispatcher("AllIssuedBooksAndReturn.jsp");
			req.setAttribute("Ilist",Ilist);
			dispatcher.include(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
