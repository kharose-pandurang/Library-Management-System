package Servlets;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Database;

public class DeleteBook extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bname=req.getParameter("bname");
		String aname=req.getParameter("aname");
		Database database=new Database();
		try {
			ResultSet blist=database.deleteBook(bname);
			if(blist!=null) {
				
				System.out.println("done here");
				RequestDispatcher dispatcher = req.getRequestDispatcher("deletebook.jsp");
				req.setAttribute("blist", blist);
				req.setAttribute("msg", "yes");
				System.out.println("done...");
				dispatcher.include(req, resp);
			}
			else {
				RequestDispatcher dispatcher = req.getRequestDispatcher("deletebook.jsp");
				ResultSet oldblist=database.getAllBooks();
				req.setAttribute("blist", oldblist);
				req.setAttribute("msg", "no");
				System.out.println("done...");
				dispatcher.include(req, resp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
