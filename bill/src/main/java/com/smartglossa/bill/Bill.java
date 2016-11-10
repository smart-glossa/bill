package com.smartglossa.bill;

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

import org.json.JSONObject;

public class Bill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String op = request.getParameter("operation");
	    
	    if (op.equals("addProduct")) {
	        int productId = Integer.parseInt(request.getParameter("pid"));
	        String name = request.getParameter("pname");
	        float cost = Float.parseFloat(request.getParameter("cost"));
	        
	        // Add Product
	        try {
	            Class.forName("com.mysql.jdbc.Driver") ;
	            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "") ;
	            Statement stmt = conn.createStatement() ;
	            String query = "Insert into product value(" + productId + ", '" + name + "', " + cost +")";
	            stmt.execute(query) ;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    } else if (op.equals("getProduct")) {
	        int pid = Integer.parseInt(request.getParameter("pid"));
	        
	        try {
                Class.forName("com.mysql.jdbc.Driver") ;
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "") ;
                Statement stmt = conn.createStatement() ;
                String query = "select * from product where productId = " + pid;
                ResultSet rs = stmt.executeQuery(query) ;
                JSONObject obj = new JSONObject();
                if (rs.next())  {
                    obj.put("name", rs.getString(2));
                    obj.put("cost", rs.getFloat(3));
                }
                response.getWriter().print(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
	    }
	    
	   
	    
	}

   

}
