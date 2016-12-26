
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
		if (operation.equals("cusAdd")) {
			int cid = Integer.parseInt(request.getParameter("cid"));
			String cname = request.getParameter("cname");
			String caddr = request.getParameter("caddr");
			int cphno = Integer.parseInt(request.getParameter("cphno"));
			JSONObject result = new JSONObject();
			try {
				CustomerClass cus = new CustomerClass();
				cus.cusAdd(cid, cname, caddr, cphno);
				result.put("status", 1);
			} catch (Exception e) {
				result.put("status", 0);
				e.printStackTrace();

			}
			response.getWriter().print(result);
		} else if (operation.equals("cusUpdate")) {
			int cid = Integer.parseInt(request.getParameter("cid"));
			String cname = request.getParameter("cname");
			String caddr = request.getParameter("caddr");
			int cphno = Integer.parseInt(request.getParameter("cphno"));
			JSONObject result = new JSONObject();
			try {
				CustomerClass cus = new CustomerClass();
				cus.cusUpdate(cid, cname, caddr, cphno);
				result.put("status", 1);
			} catch (Exception e) {
				result.put("status", 0);
				e.printStackTrace();

			}
			response.getWriter().print(result);
		} else if (operation.equals("cusDelete")) {
			int cid = Integer.parseInt(request.getParameter("cid"));
			JSONObject result = new JSONObject();
			try {
				CustomerClass cus = new CustomerClass();
				cus.cusDelete(cid);
				result.put("status", 1);
			} catch (Exception e) {
				result.put("status", 0);
				e.printStackTrace();

			}
			response.getWriter().print(result);
		} else if (operation.equals("cusOne")) {
			int id = Integer.parseInt(request.getParameter("id"));
			JSONObject result = new JSONObject();
			try {
				CustomerClass cus = new CustomerClass();
				cus.cusOne(id);

			}

			catch (Exception e) {
				result.put("status", 0);
				e.printStackTrace();

			}
			response.getWriter().print(result);
		} else if (operation.equals("cusAll")) {
			JSONArray result= new JSONArray();
			try {
				CustomerClass cus=new CustomerClass();
				cus.cusAll();
				
				
			} catch (Exception e) {
				JSONObject get=new JSONObject();
				get.put("status", 0);
				result.put(get);
				
				e.printStackTrace();

			}
			response.getWriter().print(result);
		} else if (operation.equals("cusale")) {
			int cuid = Integer.parseInt(request.getParameter("customerId"));
			JSONArray result = new JSONArray();

			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement stat = con.createStatement();
				String query = "select saleId from customerbill where customerId=" + cuid;
				ResultSet rs = stat.executeQuery(query);
				while (rs.next()) {
					int saleId = rs.getInt("saleId");
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
					Statement state = conn.createStatement();
					String queryy = "select * from salemetadata,salelineitems,salepayment where salemetadata.saleId="
							+ saleId + " AND salelineitems.saleId =" + saleId + " AND salepayment.saleId =" + saleId;
					ResultSet res = state.executeQuery(queryy);
					while (res.next()) {
						JSONObject obj = new JSONObject();
						obj.put("billDate", res.getDate("billDate"));
						obj.put("vat", res.getFloat("vat"));
						obj.put("discount", res.getFloat("discount"));
						obj.put("billTotal", res.getFloat("billTotal"));
						obj.put("saleLineId", res.getInt("saleLineId"));
						obj.put("productId", res.getInt("productId"));
						obj.put("quantity", res.getFloat("quantity"));
						obj.put("cost", res.getFloat("cost"));
						obj.put("payId", res.getInt("payId"));
						obj.put("payDate", res.getDate("payDate"));
						obj.put("paidAmount", res.getFloat("paidAmount"));
						result.put(obj);

					}
				}

			} catch (Exception e) {
				JSONObject obj = new JSONObject();
				obj.put("status", "0");
				result.put(obj);
				e.printStackTrace();

			}
			response.getWriter().print(result);

		}
	}
}