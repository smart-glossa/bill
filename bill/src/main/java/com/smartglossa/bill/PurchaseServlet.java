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

public class PurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PurchaseServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request, response);      
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if (operation.equals("addPurchase")) {
			JSONObject obj = new JSONObject();
			int purchaseId = Integer.parseInt(request.getParameter("purchaseId"));
			String billDate = request.getParameter("billDate");
			float vat = Float.parseFloat(request.getParameter("vat"));
			float discount = Float.parseFloat(request.getParameter("discount"));
			float billTotal = Float.parseFloat(request.getParameter("billTotal"));
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "insert into purchaseMetaData(purchaseId,billDate,vat,discount,billTotal) values("
						+ purchaseId + ",'" + billDate + "'," + vat + "," + discount + "," + billTotal + ")";
				stmt.execute(query);
				obj.put("status", "success");
			} catch (Exception e) {
				obj.put("status", "failure");
				e.printStackTrace();
			}
			response.getWriter().print(obj);
		} else if (operation.equals("getAll")) {
			JSONArray set = new JSONArray();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "select * from purchaseMetaData";
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					JSONObject obj = new JSONObject();
					obj.put("purchaseId", rs.getInt("purchaseId"));
					obj.put("billDate", rs.getString("billDate"));
					obj.put("vat", rs.getString("vat"));
					obj.put("discount", rs.getString("discount"));
					obj.put("billTotal", rs.getInt("billTotal"));
					set.put(obj);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().println(set);

		} else if (operation.equals("getOne")) {
			int purchaseId = Integer.parseInt(request.getParameter("purchaseId"));
			JSONObject obj = new JSONObject();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "select * from purchaseMetaData where purchaseId=" + purchaseId;
				ResultSet rs = stmt.executeQuery(query);
				if(rs.next()){
					obj.put("billDate",rs.getString("billDate"));
					obj.put("vat",rs.getString("vat"));
					obj.put("discount",rs.getString("discount"));
					obj.put("billTotal",rs.getString("billTotal"));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
               response.getWriter().println(obj);
		}else if(operation.equals("update")){
			JSONObject obj = new JSONObject();
			int purchaseId = Integer.parseInt(request.getParameter("purchaseId"));
			String billDate = request.getParameter("billDate");
			float vat = Float.parseFloat(request.getParameter("vat"));
			float discount = Float.parseFloat(request.getParameter("discount"));
			float billTotal = Float.parseFloat(request.getParameter("billTotal"));
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "update purchaseMetaData set billDate='"+ billDate +"',vat="+ vat +",discount="+discount+",billTotal="+billTotal+" where purchaseId="+purchaseId;
                stmt.execute(query);
                obj.put("status", "success");
                } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			   response.getWriter().println(obj);
		}		
	}
	}