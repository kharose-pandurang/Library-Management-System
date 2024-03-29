package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Book;
import DTO.Database;

public class AddBook extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bname=req.getParameter("bname");
		bname=bname.trim();
		String aname=req.getParameter("aname");
		String sub=req.getParameter("subname");
		int count=Integer.parseInt(req.getParameter("bcount"));
		Book b=new Book();
		b.setAname(aname);
		b.setBname(bname);
		b.setBsub(sub);
		b.setCount(count);
		
		System.out.println(b.toString());
		Database data=new Database();
		try {
			if(data.AddBook(b)) {
				RequestDispatcher dispatcher=req.getRequestDispatcher("addbook.jsp");
				req.setAttribute("msg","yes");
				dispatcher.include(req, resp);
			}
			else {
				RequestDispatcher dispatcher=req.getRequestDispatcher("addbook.jsp");
				req.setAttribute("msg","no");
				dispatcher.include(req, resp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
