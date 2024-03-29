package Servlets;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Database;

public class GrantReturn extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sid=req.getParameter("sid");
		String bname=req.getParameter("bname");
		Database database=new Database();
		try {
			if(database.verifyReturn(sid, bname)) {
				ResultSet relist = database.getAllReturnRequests();
				ResultSet dlist = database.getAllRequests();
				System.out.println("excelent");
				RequestDispatcher dispatcher = req.getRequestDispatcher("requests.jsp");
				req.setAttribute("relist", relist);
				req.setAttribute("rlist", dlist);
				req.setAttribute("msg1", "yes");
				req.setAttribute("msg", "");
				System.out.println("done...");
				dispatcher.include(req, resp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
