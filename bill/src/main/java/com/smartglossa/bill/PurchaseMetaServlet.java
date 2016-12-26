package com.smartglossa.bill;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class PurchaseMetaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PurchaseMetaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if (operation.equals("addItem")) {
			JSONObject obj = new JSONObject();
			int purchaseId = Integer.parseInt(request.getParameter("purchaseId"));
			int productId = Integer.parseInt(request.getParameter("productId"));
			float quantity = Float.parseFloat(request.getParameter("quantity"));
			float buyPrice = Float.parseFloat(request.getParameter("buyPrice"));
			float sellPrice = Float.parseFloat(request.getParameter("sellPrice"));
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "insert into purchaseLineItem(purchaseId,productId,quantity,buyPrice,sellPrice) values("
						+ purchaseId + "," + productId + "," + quantity + "," + buyPrice + "," + sellPrice + ")";
				stmt.execute(query);
				obj.put("status", "success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().print(obj);
		} else if (operation.equals("getAllItem")) {
			JSONArray array = new JSONArray();
			{
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
					Statement stmt = conn.createStatement();
					String query = "select * from purchaseLineItem";
					ResultSet rs = stmt.executeQuery(query);
					while (rs.next()) {
						JSONObject obj = new JSONObject();
						obj.put("purchaseId", rs.getInt("purchaseId"));
						obj.put("productId", rs.getString("productId"));
						obj.put("quantity", rs.getString("quantity"));
						obj.put("sellPrice", rs.getString("sellPrice"));
						obj.put("buyPrice", rs.getString("buyPrice"));
						array.put(obj);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.getWriter().println(array);
			}
		} else if (operation.equals("delete")) {
			int purchaselineid = Integer.parseInt(request.getParameter("purchaseLineId"));
			JSONObject obj = new JSONObject();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "delete from purchaseLineItem where purchaseLineId=" + purchaselineid;
				stmt.execute(query);
				obj.put("status", "success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().println(obj);
		}else if(operation.equals("getId")){
			int productId = Integer.parseInt(request.getParameter("productId"));
			JSONObject obj = new JSONObject();
			try {
				Class.forName("com.mysql.jdbc.Driver"); 
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "select * from purchaseLineItem,product where purchaseLineItem.productId=product.productId";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next());{
                	obj.put("pName", rs.getDate("pName"));
					obj.put("buyPrice",rs.getFloat("buyPrice"));
					obj.put("sellPrice", rs.getFloat("sellPrice"));
					obj.put("billTottal", rs.getFloat("billTotal"));
					obj.put("quantity", rs.getFloat("quantity"));
					obj.put("buyPrice", rs.getFloat("buyPrice"));
					obj.put("sellPrice", rs.getFloat("sellPrice"));
                }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(operation.equals("getId")){
				int productId = Integer.getInteger(request.getParameter("productId"));
				JSONObject obj = new JSONObject();
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
					Statement stmt = conn.createStatement();
					String query = "select * from purchaseLineItem,product where purchaseLineItem.productId=product.productId";
                    ResultSet rs = stmt.executeQuery(query);
                    while(rs.next());{
                    	obj.put("pName", rs.getDate("pName"));
    					obj.put("buyPrice",rs.getFloat("buyPrice"));
    					obj.put("sellPrice", rs.getFloat("sellPrice"));
    					obj.put("billTottal", rs.getFloat("billTotal"));
    					obj.put("quantity", rs.getFloat("quantity"));
    					obj.put("buyPrice", rs.getFloat("buyPrice"));
    					obj.put("sellPrice", rs.getFloat("sellPrice"));
    					obj.put("payDate", rs.getDate("payDate"));
                    }
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
}