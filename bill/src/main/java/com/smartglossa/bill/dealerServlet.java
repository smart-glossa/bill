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
				dealerClass dealer = new dealerClass();
				dealer.adddealer(dealerId, name, address, phoneNumber, TINNumber);
				objec.put("status1", "1");
			} catch (Exception e) {
				objec.put("status", "0");
				e.printStackTrace();
			}
			response.getWriter().println(objec);
		} else if (operation.equals("getall")) {
		    JSONArray result=new JSONArray();
			try {
				dealerClass dealer = new dealerClass();
				result=dealer.getall();
                }catch (Exception e) {
						JSONObject get=new JSONObject();
						get.put("status",0);
						result.put(get);
                       e.printStackTrace();
			}
			response.getWriter().println(result);
		} else if (operation.equals("getone")) {
			int dealerId = Integer.parseInt(request.getParameter("dealerId"));
			JSONObject one = new JSONObject();
			try{
				dealerClass dealer = new dealerClass();
				one = dealer.getone(dealerId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.getWriter().println(one);
		} else if (operation.equals("delete")) {
			int dealerId = Integer.parseInt(request.getParameter("dealerId"));
			JSONObject delete = new JSONObject();
			try {
				dealerClass dealer = new dealerClass();
				dealer.deletedealer(dealerId);
			} catch (Exception e) {
				delete.put("status", "0");
				e.printStackTrace();
			}
			response.getWriter().println(delete);

		} else if (operation.equals("update")) {
			int dealerId = Integer.parseInt(request.getParameter("dealerId"));
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String phoneNumber = request.getParameter("phoneNumber");
			String TINNumber = request.getParameter("TINNUmber");
			JSONObject update = new JSONObject();
			try {
				dealerClass dealer = new dealerClass();
				dealer.updatedealer(dealerId, name, address, phoneNumber, TINNumber);
				update.put("status", "1");
			} catch (Exception e) {
				update.put("message", "error");
				e.printStackTrace();
			}
			response.getWriter().println(update);

		} else if (operation.equals("billadd")) {
			int dId = Integer.parseInt(request.getParameter("dealerId"));
			JSONArray bill = new JSONArray();
			try {
				dealerClass dealer = new dealerClass();
				bill=dealer.dealerbill(dId);
			} catch (Exception e) {
				JSONObject all = new JSONObject();
				all.put("status","0");
				bill.put(all);
				e.printStackTrace();
			}
			response.getWriter().println(bill);

		}

	}
}
