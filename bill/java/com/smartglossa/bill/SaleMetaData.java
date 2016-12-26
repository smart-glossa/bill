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

public class SaleMetaData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SaleMetaData() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		  if(operation.equals("addSaleMetaData")){
			  JSONObject obj = new JSONObject();
			  int saleId = Integer.parseInt(request.getParameter("saleid"));
	          String billDate = request.getParameter("billdate");
			  float vat = Float.parseFloat(request.getParameter("vat"));
			  float discount = Float.parseFloat(request.getParameter("discount"));
			  float billTotal = Float.parseFloat(request.getParameter("billtotal"));
			  try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "insert into salemetadata(saleId,billDate,vat,discount,billTotal)values("+ saleId +",'"+ billDate +"',"+ vat +","+ discount +","+ billTotal +")";
				stmt.execute(query);
				obj.put("status","success");
				} catch (Exception e) {
					obj.put("status","failure");
					e.printStackTrace();
			}
			  response.getWriter().print(obj);
		  }else if (operation.equals("getSaleMetaData")) {
	            JSONArray result = new JSONArray();
	            try {
	                Class.forName("com.mysql.jdbc.Driver");
	                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
	                Statement stmt = conn.createStatement();
	                String query = "select *from salemetadata";
	                ResultSet rs = stmt.executeQuery(query);
	                while (rs.next()) {
	                    JSONObject obj = new JSONObject();
	                    obj.put("saleId", rs.getInt("saleid"));
	                    obj.put("billDate", rs.getString("billdate"));
	                    obj.put("vat", rs.getFloat("vat"));
	                    obj.put("discount",rs.getFloat("discount"));
	                    obj.put("billTotal", rs.getFloat("billtotal"));
	                    result.put(obj);
	                }
	            } catch (Exception e) {
	                JSONObject obj = new JSONObject();
	                obj.put("status", "0");
	                result.put(obj);
	                e.printStackTrace();
	            }
	            response.getWriter().print(result);
	        }else if(operation.equals("getOneSaleMetaData")){
	        	
	        }
	}

}
