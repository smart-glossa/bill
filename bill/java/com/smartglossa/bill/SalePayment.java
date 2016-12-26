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



public class SalePayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SalePayment() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("operation");
		  if(operation.equals("addSalePayment")){
			  JSONObject obj = new JSONObject();
			  int saleId = Integer.parseInt(request.getParameter("saleid"));
	          String payDate = request.getParameter("paydate");
			  float paidAmount = Float.parseFloat(request.getParameter("paidAmount"));
			  try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement stmt = conn.createStatement();
				String query = "insert into salepayment(saleId,payDate,paidAmount)values("+ saleId +",'"+ payDate +"',"+ paidAmount +")";
				stmt.execute(query);
				obj.put("status","success");
				} catch (Exception e) {
					obj.put("status","failure");
					e.printStackTrace();
			}
			  response.getWriter().print(obj);
		  }else if (operation.equals("getSalePayment")) {
	          JSONArray result = new JSONArray();
	          try {
	              Class.forName("com.mysql.jdbc.Driver");
	              Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
	              Statement stmt = conn.createStatement();
	              String query = "select *from salepayment";
	              ResultSet rs = stmt.executeQuery(query);
	              while (rs.next()) {
	                  JSONObject obj = new JSONObject();
	                  obj.put("payId",rs.getInt("payid"));
	                  obj.put("saleId", rs.getInt("saleid"));
	                  obj.put("payDate", rs.getString("paydate"));
	                  obj.put("paidAmount", rs.getFloat("paidAmount"));
	                  result.put(obj);
	              }
	          } catch (Exception e) {
	              JSONObject obj = new JSONObject();
	              obj.put("status", "0");
	              result.put(obj);
	              e.printStackTrace();
	          }
	          response.getWriter().print(result);
	      } else if(operation.equals("getOneSalePayment")){
	          JSONObject obj = new JSONObject();
	          int payId = Integer.parseInt(request.getParameter("payid"));
	          try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
		          Statement stmt = conn.createStatement();
		          String query = "select * from salepayment where payId="+ payId;
		          ResultSet rs = stmt.executeQuery(query);
		          if (rs.next()) {
		        	  obj.put("saleId", rs.getInt("saleid"));
	                  obj.put("payDate", rs.getString("paydate"));
	                  obj.put("paidAmount",rs.getFloat("paidamount"));
		          }
			} catch (Exception e) {
				obj.put("status","0");
				e.printStackTrace();
			}
	          response.getWriter().print(obj);
	      }else if(operation.equals("deleteSalePayment")){
	    	  int payId = Integer.parseInt(request.getParameter("payid"));
	    	  JSONObject obj= new JSONObject();
	    	  try {
	                Class.forName("com.mysql.jdbc.Driver");
	                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
	                Statement stmt = conn.createStatement();
	                String query = "Delete from salepayment where payId=" + payId;
	                stmt.execute(query);
	                obj.put("status", "1");
	            } catch (Exception e) {
	                obj.put("status", "0");
	                e.printStackTrace();
	            }
	            response.getWriter().print(obj);
	        }else if (operation.equals("updateSalePayment")) {
				JSONObject obj = new JSONObject();
				  int payId = Integer.parseInt(request.getParameter("payid"));
				  int saleId = Integer.parseInt(request.getParameter("saleid"));
		          String payDate = request.getParameter("paydate");
				  float paidAmount = Float.parseFloat(request.getParameter("paidAmount"));
				try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
						Statement stmt = conn.createStatement();
				        String query = "update salepayment set saleId=" + saleId +",payDate='"+ payDate +"',paidAmount=" + paidAmount +" where payId=" +payId ;
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


