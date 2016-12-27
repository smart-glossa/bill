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

public class CatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String operation = request.getParameter("operation");
		if (operation.equals("add")) {
			int cid = Integer.parseInt(request.getParameter("catid"));
			String cname = request.getParameter("cname");
			JSONObject obj = new JSONObject();
			try {
				CategoryClass cat=new CategoryClass();
				cat.add(cid, cname);
				obj.put("status", "success");
			} catch (Exception e) {
				e.printStackTrace();
			}
			response.getWriter().println(obj);
		} else if (operation.equals("update")) {
			int cid = Integer.parseInt(request.getParameter("catid"));
			String cname = request.getParameter("cname");
			JSONObject res = new JSONObject();
			try {
				CategoryClass cate=new CategoryClass();
				cate.update(cid, cname);
				res.put("status", 1);

			} catch (Exception e) {
				res.put("status", 0);
				e.printStackTrace();

			}
			response.getWriter().println(res);

		} else if (operation.equals("delete")) {
			int cid = Integer.parseInt(request.getParameter("catid"));
			JSONObject ress = new JSONObject();
			try {
				CategoryClass catego=new CategoryClass();
				catego.delete(cid);
				ress.put("status", 1);

			} catch (Exception e) {
				ress.put("status", 0);
				e.printStackTrace();

			}
			response.getWriter().println(ress);

		} else if (operation.equals("getOne")) {
			int cid = Integer.parseInt(request.getParameter("catid"));
			JSONObject obj = new JSONObject();
			try {
				
				CategoryClass categ=new CategoryClass();
				obj = categ.getOne(cid);
				

			} catch (Exception e) {
				obj.put("status", 0);
				e.printStackTrace();

			}
			response.getWriter().println(obj);

		} else if (operation.equals("getAll")) {
			JSONArray resu = new JSONArray();
			try {
				CategoryClass category=new CategoryClass();
				resu = category.getAll();
			} catch (Exception e) {
				JSONObject error = new JSONObject();
				error.put("status", 0);
				resu.put(error);
				e.printStackTrace();
			}
			response.getWriter().println(resu);
		}

	}

}
