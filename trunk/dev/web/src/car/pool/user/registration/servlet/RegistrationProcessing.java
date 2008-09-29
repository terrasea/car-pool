package car.pool.user.registration.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.verisign.joid.consumer.OpenIdFilter;

import car.pool.persistance.exception.DuplicateUserNameException;
import car.pool.persistance.exception.UserException;
import car.pool.user.User;
import car.pool.user.UserFactory;
import car.pool.user.UserManager;
import car.pool.user.registration.RandomTextGenerator;


public class RegistrationProcessing extends HttpServlet {

	/*
	 * 
	 */
	private static final long serialVersionUID = -4410555702177133894L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		register(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		register(request, response);
	}
	
	private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		//String loggedInAs = OpenIdFilter.getCurrentUser(request.getSession());
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String userName = request.getParameter("userName");
		
		if(userName == null || email == null || userName.length() == 0 || email.length() == 0) {
			request.setAttribute("error", "Please input a username and a email address");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}else if(password1 == null || password2 == null || password1.length() > 0 || password2.length() > 0) {
			if(!password1.equals(password2)) {
				// TODO make sure they get a error message and the form is prefilled with the previous values they entered 
				response.sendRedirect(String.format("%s/register.jsp", request.getContextPath()));
				return;
			}
		} else {
			request.setAttribute("error", "Please input a password");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		
		String verifyText = request.getParameter("verifytext");
		if(verifyText == null || verifyText.length() == 0) {
			request.setAttribute("error", "Please input the verifiction text displayed in the image");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		
		if(!verifyText.equals(new RandomTextGenerator().get((Integer) session.getAttribute("quote_pos")))) {
			request.setAttribute("error", "Please input the correct verifiction text displayed in the image");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		
		UserManager manager = new UserManager();
		User noidUser = UserFactory.newInstance();
		noidUser.setUserName(userName);
		noidUser.setEmail(email);
		noidUser.setPassword(password1);  // it is assumed since the method got this far that password1 and password2 match
		noidUser.setPhoneNumber(phone);
		try {
			User user = manager.registerUser(noidUser);
			session.setAttribute("user", user);
			session.setAttribute("signedin", Boolean.TRUE);
			String s = String.format("%s/welcome.jsp", request.getContextPath());
			response.sendRedirect(s);
			return;
		} catch (DuplicateUserNameException e) {
			// TODO make sure a error message is shown for this user telling them why they couldn't be registered
			String s = String.format("%s/register.jsp", request.getContextPath());
			response.sendRedirect(s);
			return;
		} catch (UserException e) {
			// TODO make sure a error message is shown for this user telling them why they couldn't be registered
			response.sendRedirect(String.format("%s/register.jsp", request.getContextPath()));
			return;
		} catch (SQLException e) {
			// TODO make sure a error message is shown for this user telling them why they couldn't be registered
			response.sendRedirect(String.format("%s/register.jsp", request.getContextPath()));
			return;
		}
	}
}