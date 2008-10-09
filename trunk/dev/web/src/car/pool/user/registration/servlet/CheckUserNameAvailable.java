package car.pool.user.registration.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.pool.persistance.CarPoolStore;
import car.pool.persistance.CarPoolStoreImpl;

public class CheckUserNameAvailable extends HttpServlet {
	private static final long serialVersionUID = 7481030857181249177L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		if(username != null) {
			CarPoolStore store = new CarPoolStoreImpl();
			if(store.checkUserExists(username) || username.toLowerCase().startsWith("admin")) {
				out.print("false");
			} else {
				out.print("true");
			}
		} else {
			out.println("Error: Parameter username does not exist");
		}
		
		out.close();
	}
}
