package Servlets;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Database;

public class GrantRequests extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid=req.getParameter("sid");
		String bname=req.getParameter("bname");
		System.out.println(sid);
		Database database=new Database();
		try {
			if(database.verifyIssueBook(sid, bname)) {
				ResultSet dlist = database.getAllRequests();
				ResultSet relist = database.getAllReturnRequests();
				System.out.println("excelent");
				RequestDispatcher dispatcher = req.getRequestDispatcher("requests.jsp");
				req.setAttribute("rlist", dlist);
				req.setAttribute("relist", dlist);
				req.setAttribute("msg", "yes");
				req.setAttribute("msg1", "");
				System.out.println("done...");
				dispatcher.include(req, resp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
