package guestbook;

import java.io.IOException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class GuestbookServlet extends HttpServlet 
{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException 
	{
		UserService userService = UserServiceFactory.getUserService();        
		User user = userService.getCurrentUser();       
		
		if (user != null) 
		{            
			resp.setContentType("text/plain");            
			resp.getWriter().println("Hello Mr." + user.getNickname() + " sir.");
			resp.setContentType("text/html");
			resp.getWriter().println("<br><a href= " + userService.createLogoutURL(req.getRequestURI()) + " >Logout</a>");
		}
		else 
		{            
			resp.sendRedirect(userService.createLoginURL(req.getRequestURI()));
		}
	}
}
