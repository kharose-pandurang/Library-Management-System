package Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminLogin extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String amail=req.getParameter("amail");
		String apass=req.getParameter("apass");
		if(amail.equals("admin@mail.com") && apass.equals("admin")) {
			RequestDispatcher dispatcher=req.getRequestDispatcher("adminpage.jsp");
			req.setAttribute("msg","yes");
			dispatcher.include(req, resp);
		}
		else {
			RequestDispatcher dispatcher=req.getRequestDispatcher("lloginpage.jsp");
			req.setAttribute("msg","no");
			dispatcher.include(req, resp);
		}
	}
}
