package com.smartglossa.salelineitems;

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


public class SaleLineItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SaleLineItem() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String operation = request.getParameter("operation");
	  if(operation.equals("addSaleLine")){
		  JSONObject obj = new JSONObject();
		  int saleId = Integer.parseInt(request.getParameter("saleid"));
		  int productId = Integer.parseInt(request.getParameter("productId"));
		  float quantity = Float.parseFloat(request.getParameter("quantity"));
		  float cost = Float.parseFloat(request.getParameter("cost"));
		  try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
			Statement stmt = conn.createStatement();
			String query = "insert into salelineitems(saleId,productId,quantity,cost)values("+ saleId +","+ productId +","+ quantity +","+ cost +")";
			stmt.execute(query);
			obj.put("status","success");
			} catch (Exception e) {
				obj.put("status","failure");
				e.printStackTrace();
		}
		  response.getWriter().print(obj);
	  }else if (operation.equals("getSaleLineItem")) {
          JSONArray result = new JSONArray();
          try {
              Class.forName("com.mysql.jdbc.Driver");
              Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
              Statement stmt = conn.createStatement();
              String query = "select *from salelineitems";
              ResultSet rs = stmt.executeQuery(query);
              while (rs.next()) {
                  JSONObject obj = new JSONObject();
                  obj.put("saleLineId",rs.getInt("salelineid"));
                  obj.put("saleId", rs.getInt("saleid"));
                  obj.put("productId", rs.getInt("productid"));
                  obj.put("quantity", rs.getFloat("quantity"));
                  obj.put("cost",rs.getFloat("cost"));
                  result.put(obj);
              }
          } catch (Exception e) {
              JSONObject obj = new JSONObject();
              obj.put("status", "0");
              result.put(obj);
              e.printStackTrace();
          }
          response.getWriter().print(result);
      }else if(operation.equals("getOneSaleLineItem")){
          JSONObject obj = new JSONObject();
          String saleLineId = request.getParameter("salelineid");
          try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
	          Statement stmt = conn.createStatement();
	          String query = "select * from salelineitems where saleLineId="+ saleLineId;
	          ResultSet rs = stmt.executeQuery(query);
	          if (rs.next()) {
	        	  obj.put("saleId", rs.getInt("saleid"));
                  obj.put("productId", rs.getInt("productid"));
                  obj.put("quantity", rs.getFloat("quantity"));
                  obj.put("cost",rs.getFloat("cost"));
	          }
		} catch (Exception e) {
			obj.put("status","0");
			e.printStackTrace();
		}
          response.getWriter().print(obj);
      }else if(operation.equals("deleteSaleLineItem")){
    	  int saleLineId = Integer.parseInt(request.getParameter("salelineid"));
    	  JSONObject obj= new JSONObject();
    	  try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
                Statement stmt = conn.createStatement();
                String query = "Delete from salelineitems where saleLineId=" + saleLineId;
                stmt.execute(query);
                obj.put("status", "1");
            } catch (Exception e) {
                obj.put("status", "0");
                e.printStackTrace();
            }
            response.getWriter().print(obj);
        }else if (operation.equals("updateSaleLineItem")) {
			JSONObject obj = new JSONObject();
			  int saleLineId = Integer.parseInt(request.getParameter("salelineid"));
			  int saleId = Integer.parseInt(request.getParameter("saleid"));
	          int productId = Integer.parseInt(request.getParameter("productid"));
			  float quantity = Float.parseFloat(request.getParameter("quantity"));
			  float cost = Float.parseFloat(request.getParameter("cost"));
			try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
					Statement stmt = conn.createStatement();
			        String query = "update salelineitems set saleId=" + saleId +",productId="+ productId +",quantity=" + quantity +",cost="+ cost+" where saleLineId=" +saleLineId ;
				 stmt.execute(query);
				 obj.put("status","Success");
			} catch (Exception e) {
				obj.put("status", "Failure");
				e.printStackTrace();
			}
			response.getWriter().print(obj);

		}
	}
}
