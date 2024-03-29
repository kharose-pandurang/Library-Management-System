package Servlets;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Database;

public class loadIssueBookpage extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Database database = new Database();
		try {
			ResultSet dlist = database.getAllBooks();
			System.out.println("done here");
			RequestDispatcher dispatcher = req.getRequestDispatcher("IssueABook.jsp");
			req.setAttribute("blist", dlist);
			req.setAttribute("msg", "");
			System.out.println("done...");
			dispatcher.include(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
