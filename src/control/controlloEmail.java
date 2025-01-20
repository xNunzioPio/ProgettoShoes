package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDM;

@WebServlet("/controlloEmail")
public class controlloEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static UserDM usr=new UserDM();
	
	public controlloEmail() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String email=request.getParameter("email");
		
		try {
			if(!usr.serachEmail(email))
				out.write("Email disponibile");
			else
				out.write("Email gi&agrave; presente");
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}



}





