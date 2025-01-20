package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/r")
public class PageControl extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String dest=req.getParameter("param")+".html";
		System.out.println(dest);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher(dest);
		requestDispatcher.forward(req, resp);
	}	
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
}	
}
