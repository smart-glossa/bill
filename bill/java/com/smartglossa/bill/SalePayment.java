package com.smartglossa.bill;

import java.io.IOException;

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
				SalePaymentClass payment = new SalePaymentClass();
				payment.addSalePayment(saleId, payDate, paidAmount);
				obj.put("status","success");
				} catch (Exception e) {
					obj.put("status","failure");
					e.printStackTrace();
			}
			  response.getWriter().print(obj);
		  }else if (operation.equals("getSalePayment")) {
	          JSONArray result = new JSONArray();
	          try {
	        	  SalePaymentClass payment = new SalePaymentClass();
	        	  result= payment.getSalePayment();
	          } catch (Exception e) {
	              e.printStackTrace();
	          }
	          response.getWriter().print(result);
	      } else if(operation.equals("getOneSalePayment")){
	          JSONObject obj = new JSONObject();
	          int payId = Integer.parseInt(request.getParameter("payid"));
	          try {
	        	  SalePaymentClass payment = new SalePaymentClass();
	        	  obj = payment.getOneSalePayment(payId);
			} catch (Exception e) {
				e.printStackTrace();
			}
	          response.getWriter().print(obj); 
	      }else if(operation.equals("deleteSalePayment")){
	    	  int payId = Integer.parseInt(request.getParameter("payid"));
	    	  JSONObject obj= new JSONObject();
	    	  try {
	               SalePaymentClass  payment = new SalePaymentClass();
	     payment.deleteSalePayment(payId);
	               obj.put("status", "Success");
	            } catch (Exception e) {
	            	 obj.put("status","Failure");
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
					  SalePaymentClass  payment = new SalePaymentClass();
					  payment.updateSalePayment(payId, saleId, payDate, paidAmount);
					 obj.put("status","Success");
				} catch (Exception e) {
					obj.put("status", "Failure");
					e.printStackTrace();
				}
				response.getWriter().print(obj);
	        }
	}
	}


