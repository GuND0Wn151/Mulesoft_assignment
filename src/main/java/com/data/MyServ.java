package com.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/MyServ")
public class MyServ extends HttpServlet{
	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		PrintWriter pw= response.getWriter();
		pw.write("Connected");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/movies","root","system");
			String val=request.getParameter("insert");
			if(val!=null) {
				String name=request.getParameter("name");
				String actor=request.getParameter("actor");
				String actress=request.getParameter("actress");
				String director=request.getParameter("director");
				String yor=request.getParameter("yor");
				String query="insert into movies (name,actor,actress,director,year_of_release)"+" values(?,?,?,?,?)";
				PreparedStatement stmt=conn.prepareStatement(query);
				stmt.setString(1, name);
				stmt.setString(2, actor);
				stmt.setString(3, actress);
				stmt.setString(4, director);
				stmt.setString(5, yor);
				stmt.execute();
				pw.write("<p> Inserted Successfull! </p>");
			}
			else {
				Statement stm=conn.createStatement();
				ResultSet rs=stm.executeQuery("select * from movies");
				pw.write("<table border='2'>");
				pw.write("<tr><td>Name</td><td>Actor</td><td>Actress</td><td>Director</td><td>Year_of_release</td></tr>");
				while(rs.next()) {
					pw.write("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td></tr>");
				}
				pw.write("</table>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
}
