package session.ser;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import session.db.CrudOperation;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		HttpSession hs=null;
		String ui=request.getParameter("text-userid");
		String upass=request.getParameter("text-pass");
		System.out.println(ui+upass);
		
			if(ui.equals("rish")&& upass.equals("2108"))
			{
				
					hs=request.getSession(); /*create session*/
					hs.setAttribute("userinfo", ui);
					response.sendRedirect("/SessionMining/jsp/front.jsp");
				
				
				}
			
				else
				{ String msg="entered wrong id or password";
					response.sendRedirect("/SessionMining/jsp/login.jsp?msg");
				}
	}
	}

