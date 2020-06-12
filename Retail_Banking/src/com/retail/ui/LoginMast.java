package com.retail.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginMast extends HttpServlet  
{
    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
    	String uname="",password="";
    	PrintWriter pw=res.getWriter();
        res.setContentType("text/html");
        try
        {
             Class.forName("oracle.jdbc.driver.OracleDriver");
             Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","c##kevin","1234");
             Statement st=con.createStatement();
             System.out.println("connection established successfully...!!");     

             ResultSet rs=st.executeQuery("SELECT login_id,password FROM usermast");
             
             uname=req.getParameter("uname");
             password=req.getParameter("password");

             //pw.println("<table border=1>");
                 while(rs.next())
                 {
                     if(uname.equals(rs.getString("login_id")) && password.equals(rs.getString("password")))
                     {
                    	 pw.println("<h1>Login Successfull</h1>");
                     }
                     else
                     {                    	 
                    	 pw.println("<h1>Login Credentials Wrong</h1>");
                     }
                	 //pw.println("<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>");
                 }
             //pw.println("</table>");
             pw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
