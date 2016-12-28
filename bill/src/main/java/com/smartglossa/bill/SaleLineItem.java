package com.smartglossa.bill;


import java.io.IOException;

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
			  SaleLineItemClass Saleline = new SaleLineItemClass();
			  Saleline.addSaleLine(saleId, productId, quantity, cost);
			  obj.put("status", "success");
			} catch (Exception e) {
				obj.put("status","failure");
				e.printStackTrace();
		}
		  response.getWriter().print(obj);
	  }else if (operation.equals("getSaleLineItem")) {
          JSONArray result = new JSONArray();
          try {
        	  SaleLineItemClass Saleline = new SaleLineItemClass();
              result = Saleline.getSaleLineItem();
              }
           catch (Exception e) {
              e.printStackTrace();
          }
          response.getWriter().print(result);
      }else if(operation.equals("getOneSaleLineItem")){
          JSONObject obj = new JSONObject();
          int saleLineId = Integer.parseInt(request.getParameter("salelineid"));
          try {
        	  SaleLineItemClass Saleline = new SaleLineItemClass();
        	  obj = Saleline.getOneSaleLineItem(saleLineId);
		} catch (Exception e) {
			e.printStackTrace();
		}
          response.getWriter().print(obj);
      }else if(operation.equals("deleteSaleLineItem")){
    	  int saleLineId = Integer.parseInt(request.getParameter("salelineid"));
    	  JSONObject obj= new JSONObject();
    	  try {
               SaleLineItemClass Saleline = new SaleLineItemClass();
               Saleline.deleteSaleLineItem(saleLineId);
                obj.put("status", "Success"); 
            } catch (Exception e) {
                obj.put("status", "Failure");
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
					SaleLineItemClass Saleline = new SaleLineItemClass();
 			        Saleline.updateSaleLineItem(saleLineId, saleId, productId, quantity, cost);
				      obj.put("status","Success");
			} catch (Exception e) {
				obj.put("status", "Failure");
				e.printStackTrace();
			}
			response.getWriter().print(obj);

		}
	}
}
