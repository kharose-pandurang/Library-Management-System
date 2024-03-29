package Servlets;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Book;
import DAO.Student;
import DTO.Database;

public class AddBookReq extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid=req.getParameter("sid");
		String bname=req.getParameter("bname");
		String aname=req.getParameter("aname");
		Database database=new Database();
		try {
			Student s1=database.getStudent(sid);
			Book b1=database.getBook(bname);
			if(s1.getSid()!=null && b1.getBname()!=null) {
				if(database.requestIssueBook(s1.getSid(),b1.getBname())) {
					RequestDispatcher dispatcher = req.getRequestDispatcher("IssueABook.jsp");
					ResultSet oldblist=database.getAllBooks();
					req.setAttribute("blist", oldblist);
					req.setAttribute("msg", "yes");
					System.out.println("done...");
					dispatcher.include(req, resp);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
