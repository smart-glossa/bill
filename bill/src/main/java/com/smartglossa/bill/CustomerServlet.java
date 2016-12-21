package com.smartglossa.bill;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if (operation.equals("cusadd")) {
			int cid = Integer.parseInt(request.getParameter("cid"));
			String cname = request.getParameter("cname");
			String caddr = request.getParameter("caddr");
			int cphno = Integer.parseInt(request.getParameter("cphno"));
			JSONObject result = new JSONObject();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement stat = connection.createStatement();
				String query = "insert into customer(customerId,name,address,phonenumber)values(" + cid + ",'" + cname
						+ "','" + caddr + "'," + cphno + ")";
				stat.execute(query);
				result.put("status", 1);
			} catch (Exception e) {
				result.put("status", 0);
				e.printStackTrace();

			}
			response.getWriter().print(result);
		} else if (operation.equals("cusupdate")) {
			int cid = Integer.parseInt(request.getParameter("cid"));
			String cname = request.getParameter("cname");
			String caddr = request.getParameter("caddr");
			int cphno = Integer.parseInt(request.getParameter("cphno"));
			JSONObject cupdate = new JSONObject();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement stat = con.createStatement();
				String query = "update customer set name=' " + cname + " ',address=' " + caddr + " ',phonenumber="
						+ cphno + " where customerId=" + cid;
				stat.execute(query);
				cupdate.put("status", 1);
			} catch (Exception e) {
				cupdate.put("status", 0);
				e.printStackTrace();

			}
			response.getWriter().print(cupdate);
		} else if (operation.equals("cusdelete")) {
			int cid = Integer.parseInt(request.getParameter("cid"));
			JSONObject cdelete = new JSONObject();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement stat = con.createStatement();
				String query = "delete from customer where customerId=" + cid;
				stat.execute(query);
				cdelete.put("status", 1);
			} catch (Exception e) {
				cdelete.put("status", 0);
				e.printStackTrace();

			}
			response.getWriter().print(cdelete);
		} else if (operation.equals("cusone")) {
			int id = Integer.parseInt(request.getParameter("id"));
			JSONObject cone = new JSONObject();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement stat = con.createStatement();
				String query = "select * from customer where customerId=" + id;
				ResultSet rs = stat.executeQuery(query);
				if (rs.next()) {
					cone.put("name", rs.getString(2));
					cone.put("address", rs.getString(3));
					cone.put("phonenumber", rs.getInt(4));
				}

			} catch (Exception e) {
				cone.put("status", 0);
				e.printStackTrace();

			}
			response.getWriter().print(cone);
		} else if (operation.equals("cusAll")) {
			JSONArray call = new JSONArray();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement stat = con.createStatement();
				String query = "select * from customer";
				ResultSet res = stat.executeQuery(query);
				while (res.next()) {
					JSONObject get = new JSONObject();
					get.put("name", res.getString(2));
					get.put("address", res.getString(3));
					get.put("phonenumber", res.getInt(4));
					call.put(get);
				}

			} catch (Exception e) {
				JSONObject error = new JSONObject();
				error.put("status", 0);
				call.put(error);
				e.printStackTrace();

			}
			response.getWriter().print(call);
		}
	}

}
