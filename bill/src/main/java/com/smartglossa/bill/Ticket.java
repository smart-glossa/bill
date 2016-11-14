package com.smartglossa.bill;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


public class Ticket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		String password = "";
		if(operation.equals("add")){
		    int rId = Integer.parseInt(request.getParameter("rId"));
		    String fromJourney = request.getParameter("from");
		    String toJourney = request.getParameter("to");
		    float price = Float.parseFloat(request.getParameter("price"));
		    JSONObject obj = new JSONObject();
            try {
                Class.forName("com.mysql.jdbc.Driver") ;
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket", "root", password) ;
                Statement stmt = conn.createStatement() ;
                String query = "Insert into route value(" + rId + ", '" + fromJourney + "', '" + toJourney +"'," + price + ")";
                stmt.execute(query) ;
		}catch (Exception e) {
            obj.put("Message","Error");
            response.getWriter().print(obj);
        }
		
	}

	}
}
