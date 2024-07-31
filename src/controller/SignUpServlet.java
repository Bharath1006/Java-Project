package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mphasis.hrms.model.User;
import com.mphasis.hrms.model.UserDaoImpl;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet({ "/SignUpServlet", "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
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
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String role=request.getParameter("role");
		String email=request.getParameter("email");
		if(role.equals("Choose..."))
		{
			out.print("Invalid role. Choose correctly.");
			return;
		}
		String status="pending";
		User user=new User(userId, password, firstName, lastName, role, status, email);
		UserDaoImpl udao=new UserDaoImpl();
		int no=0;
		try {
			no=udao.create(user);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(no==1)
			out.print("Registration is successful. Click<a href=login.jsp> here </a>to login");
		else
		{
			out.print("Something wrong");
			return;
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
