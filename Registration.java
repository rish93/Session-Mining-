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

import session.db.CrudOperation;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	Connection con=null;
	PreparedStatement ps=null;
	PreparedStatement pslogin=null;
	PreparedStatement psreg=null;
	ResultSet rs=null;
	int flag=0;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
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
   		response.setContentType("text/html");
   		String ui=request.getParameter("text-userid");
   		String name=request.getParameter("text-name");
   		String upass=request.getParameter("text-pass");
   		String umail=request.getParameter("text-email");
   		String unum=request.getParameter("num");
   		long num=Long.parseLong(unum);
   		String ugender=request.getParameter("gender");
   		System.out.println(ui+name+upass+umail+num+ugender);
   		String reginsert="insert into register value(?,?,?,?,?)";
		String logininsert="insert into login value(?,?)";
		

		try{
			con=CrudOperation.createConnection();
			
			con.setAutoCommit(false);
		
			pslogin=con.prepareStatement(logininsert);
			
			pslogin.setString(1,ui);
			pslogin.setString(2, upass);
			int rw=pslogin.executeUpdate();
		
			psreg=con.prepareStatement(reginsert);
		
			psreg.setString(1, ui);
			psreg.setString(2,name);
			psreg.setString(3,umail);
			psreg.setLong(4,num);
			psreg.setString(5,ugender);
			
			int rw1=psreg.executeUpdate();
		
			if(rw>0 &&rw1>0)
			{ con.setAutoCommit(true);
				response.sendRedirect("/SessionMining/jsp/registration.jsp");	
		    }
		}
		catch(SQLException |java.lang.IllegalStateException jk)
		{
			System.out.println(jk);
		}
		finally
		{
			try
			{
				if(pslogin!=null)
				{pslogin.close();
				}
				if(psreg!=null)
				{psreg.close();
				}
				
				
			}
			catch(SQLException se)
			{
				System.out.println(se);
			}
		}
		

out.print("<font color='pink' size='10'>user id is  </font>");
   		out.print("<font color='green' size='10'>"+ui+" <br></font>");
   		out.print("<font color='pink' size='10'>user id is  </font>");
   		out.print("<font color='green' size='10'>"+name+" <br></font>");
   		out.print("<font color='pink' size='10'>user password is  </font>");
   		out.print("<font color='green' size='10'>"+upass+"<br></font>");
   		out.print("<font color='pink' size='10'>user mail is  </font>");
   		out.print("<font color='green' size='10'>"+umail+"<br></font>");
   		out.print("<font color='pink' size='10'>user phone no. is  </font>");
   		out.print("<font color='green' size='10'>"+unum+"<br></font>");
   		out.print("<font color='pink' size='10'>user gender is  </font>");
   		out.print("<font color='green' size='10'>"+ugender+"<br></font>");
   		
   		}
   }
   	
