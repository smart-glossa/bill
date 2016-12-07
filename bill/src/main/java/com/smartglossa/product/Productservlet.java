package com.smartglossa.product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class Productservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Productservlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if (operation.equals("addproducts")) {
			JSONObject obj = new JSONObject();
			int pId = Integer.parseInt(request.getParameter("productId"));
			String pname = request.getParameter("pName");
			float buyprice = Float.parseFloat(request.getParameter("buyPrice"));
			float sellprice = Float.parseFloat(request.getParameter("sellPrice"));
			float quantity = Float.parseFloat(request.getParameter("quantity"));
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qa", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "insert into product(productId,pName,buyPrice,sellPrice,quantity) values(" + pId + ",'"
						+ pname + "'," + buyprice + "," + sellprice + "," + quantity + ")";
				stmt.execute(query);
				obj.put("status", "success");
			} catch (Exception e) {
				obj.put("status", "failure");
				e.printStackTrace();
			}
			response.getWriter().print("obj");
		}

	}

}
