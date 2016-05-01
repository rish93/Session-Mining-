package session.ser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import session.db.CrudOperation;

import java.util.Date;


/**
 * Servlet implementation class Time
 */
@WebServlet("/ShowTime")
public class ShowTime extends HttpServlet {
	Connection con=null;
	PreparedStatement ps=null;
	PreparedStatement ps1=null;
	ResultSet rs1=null;
	ResultSet rs=null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowTime() {
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
		int i=0;
		PrintWriter out=response.getWriter();
   		response.setContentType("text/html");
   		System.out.println("helo");
   		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
   		System.out.println("helo1");
   		Date to=new Date();
   		Date to1=new Date();
   		Date from=new Date();
   	 System.out.println(request.getParameter("text-dateto"));
		 String to2=request.getParameter("text-dateto");
		 String sort1[]=request.getParameterValues("sort");
		 String id=request.getParameter("text-id");
		System.out.println(to2);
		System.out.println(id);
		 String from1=request.getParameter("text-datefrom");	
   	
   		try {
   	 
   			to1 = formatter.parse(to2);
   		from=formatter.parse(from1);
   		HttpSession hs=request.getSession(false);
		String uid=(String)hs.getAttribute("userinfo");

		String strupdate="select * from useraccesslog where access_time between ? and ? AND user_name=?" ;
	/*	String strupdate1="select * from useraccesslog where access_time between ? and ? AND user_name=? order by access_url desc" ;*/
		
		/*
		 if(sort1[0].equals("time"))
		 {*/
			 con=CrudOperation.createConnection();
			 ps=con.prepareStatement(strupdate);
			
			 ps.setDate(1,java.sql.Date.valueOf(from1));
			 ps.setDate(2,java.sql.Date.valueOf(to2));
			 ps.setString(3, id);
			 rs=ps.executeQuery();
			 out.println(" <body bgcolour='light blue'><div align='right' style='position:absolute;width:99%; height:5%; background-color:navy;border-width:thin;border-color:white; border-style: solid; border-radius:20px; margin-top:0% ;'>"
			 		+ "<a href='/SessionMining/Logout' style='font-size:20px; font-family:forte; text-transform:capitalize; color:white;text-align:right;text-decoration:none;'>Logout</a>&nbsp &nbsp &nbsp </div>"
					+" <div style='position:absolute;border-radius:20px; width:98%; height:24%; background-color:red; margin-top:3% ;'>"
					 +"<h1 style='font-size:40px; font-family:cooper; text-transform:capitalize; color:white;text-align:center;'> Sites visited by particular user during <br>given time Interval</h1></div>"
			 +"<div  style=' position:absolute;margin-left:0%;margin-top:17%;font-size:20px;font-family:arial ;'><table style='background-color:teal; font-size:15px;font-family:algerian;'border=1> <tr> <td style='color:maroon; font-size:20px;font-family:algerian;'> Sno</td><td width=200px style='color:maroon; font-size:20px;font-family:algerian'> Accessed URL</td></tr>");
			 while(rs.next())
			 {
			 out.println(" <tr> <td style=' font-size:15px;'>"+i+"</td><td width=200px style='background-color:teal; font-size:15px;font-family:arial; color:white;'> "+rs.getString("access_url")+"</td></tr>"); 
			 i++;
			 }
		/* }
		 else
		 {System.out.println("hlo");
			 con=CrudOperation.createConnection();
			 ps1=con.prepareStatement(strupdate1);
			
			 ps1.setDate(1,java.sql.Date.valueOf(from1));
			 ps1.setDate(2,java.sql.Date.valueOf(to2));
			 ps1.setString(3, id);
			 rs1=ps1.executeQuery();
			 out.println("<table style='background-color:pink font-size:15px;font-family:algerian'> <tr> <td> Sno</td><td width=200px style='background-color:pink font-size:15px;font-family:algerian'> Accessed URL</td></tr>");
			 while(rs1.next())
			 {
			 out.println(" <tr> <td style='color:pink font-size:15px;'>"+i+"</td><td width=200px style='background-color:pink font-size:15px;font-family:algerian'> "+rs.getString("access_url")+"</td></tr>"); 
			 i++;
			 }
			 
		 }*/
		 }
		 catch(Exception se){
				System.out.println(se);
			}
		
	}

}

