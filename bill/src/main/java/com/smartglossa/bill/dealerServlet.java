package com.smartglossa.bill;

import java.io.IOException;
import java.math.BigInteger;
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

public class dealerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject objec = new JSONObject();
		String operation = request.getParameter("operation");
		if (operation.equals("deaadd")) {
			int dealerId = Integer.parseInt(request.getParameter("dealerId"));
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String phoneNumber = request.getParameter("phoneNumber");
			String TINNumber = request.getParameter("TINNumber");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement statement = connection.createStatement();
				String query = "insert into dealer(dealerId,name,address,phoneNumber,TINNumber)values(" + dealerId
						+ ",'" + name + "','" + address + "','" + phoneNumber + "','" + TINNumber + "')";
				statement.execute(query);
				objec.put("status1", "1");
			} catch (Exception e) {
				objec.put("status", "0");
				e.printStackTrace();
			}
			response.getWriter().println(objec);
		} else if (operation.equals("getall")) {
			JSONArray all = new JSONArray();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement statement = connection.createStatement();
				String query = "select * from dealer";
				ResultSet result = statement.executeQuery(query);
				while (result.next()) {
					JSONObject get = new JSONObject();
					get.put("dealerId", result.getInt(1));
					get.put("name", result.getString(2));
					get.put("address", result.getString(3));
					get.put("phoneNumber", result.getString(4));
					get.put("TINNumber", result.getString(5));
					all.put(get);

				}

			} catch (Exception e) {

				e.printStackTrace();
			}
			response.getWriter().println(all);
		} else if (operation.equals("get")) {
			int dealerId = Integer.parseInt(request.getParameter("dealerId"));
			JSONObject ob = new JSONObject();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement statement = connection.createStatement();
				String query = "select * from dealer where dealerId=" + dealerId;
				ResultSet re = statement.executeQuery(query);
				if (re.next()) {
					ob.put("name", re.getString(2));
					ob.put("address", re.getString(3));
					ob.put("phoneNumber", re.getString(4));
					ob.put("TINNumber", re.getString(5));

				}
			} catch (Exception e) {

				e.printStackTrace();
			}
			response.getWriter().println(ob);
		} else if (operation.equals("delete")) {
			int dealerId = Integer.parseInt(request.getParameter("dealerId"));
			JSONObject obj = new JSONObject();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement statement = connection.createStatement();
				String query = "delete from dealer where dealerId=" + dealerId;
				statement.execute(query);
				obj.put("status", "1");
			} catch (Exception e) {
				obj.put("status", "0");
				e.printStackTrace();
			}
			response.getWriter().println(obj);

		} else if (operation.equals("update")) {
			int dealerId = Integer.parseInt(request.getParameter("dealerId"));
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String phoneNumber = request.getParameter("phoneNumber");
			String TINNumber = request.getParameter("TINNUmber");
			JSONObject up = new JSONObject();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement statement = connection.createStatement();
				String query = "update dealer set name='" + name + "',address='" + address + "',phoneNumber='"
						+ phoneNumber + "',TINNumber='" + TINNumber + "' where dealerId=" + dealerId;
				statement.execute(query);
				up.put("status", "1");
			} catch (Exception e) {
				up.put("message", "error");
				e.printStackTrace();
			}
			response.getWriter().println(up);

		} else if (operation.equals("billadd")) {
			int dId = Integer.parseInt(request.getParameter("dealerId"));
			JSONArray result = new JSONArray();

			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement statement = connection.createStatement();
				String query = "select purchaseId from dealerBill where dealerId=" + dId;
				ResultSet re = statement.executeQuery(query);
				while (re.next()) {
					int pId = re.getInt("purchaseId");
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
					Statement stat = conn.createStatement();
					String queryy = "select * from purchasemetadata,purchaselineitem,purchasepayment where purchasemetadata.purchaseId="+ pId + " AND purchaselineitem.purchaseId=" + pId + " AND purchasepayment.purchaseId="+ pId;
					ResultSet res = stat.executeQuery(queryy);
					while (res.next()) {
						JSONObject all = new JSONObject();
						all.put("billDate", res.getDate("billDate"));
						all.put("vat", res.getFloat("vat"));
						all.put("discount", res.getFloat("discount"));
						all.put("billTotal", res.getFloat("billTotal"));
						all.put("productId", res.getInt("productId"));
						all.put("purchaseLineId", res.getInt("purchaseLineId"));
						all.put("quantity", res.getFloat("quantity"));
						all.put("payId", res.getInt("payId"));
						all.put("payDate", res.getDate("payDate"));
						all.put("paidAmount", res.getFloat("paidAmount"));
						result.put(all);
					}

				}
			} catch (Exception e) {
				JSONObject all = new JSONObject();
				all.put("status","0");
				result.put(all);
				e.printStackTrace();
			}
			response.getWriter().println(result);

		}

	}
}
