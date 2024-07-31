package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mphasis.hrms.model.User;
import com.mphasis.hrms.model.UserDaoImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({ "/LoginServlet", "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		String rememberMe=request.getParameter("chkRememberMe");
		UserDaoImpl udao=new UserDaoImpl();
		User user =null;
		try {
			user=udao.read(userId, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(user==null)
		{
			//login failed
			out.print("Login failed. Click<a href=login.jsp> here </a>to go back and try again.");
			return;
		}else
		{
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			//remember me
			if(request.getParameter("chkRememberMe")!=null)
			{
				//cookie
				Cookie userIdCookie=new Cookie("userId",userId);
				Cookie passwordCookie=new Cookie("password", password);
				userIdCookie.setMaxAge(1000*60*60*24*10);
				passwordCookie.setMaxAge(1000*60*60*24*10);
				response.addCookie(userIdCookie);
				response.addCookie(passwordCookie);
				System.out.println("Cookies are created");
			}
			response.sendRedirect("home.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
