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
			JSONObject result = new JSONObject();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement stat = con.createStatement();
				String query = "insert into expensecategory(catid,cname) values(" + cid + ",'" + cname + "')";
				stat.execute(query);
				result.put("status", 1);
			} catch (Exception e) {
				result.put("status", 0);
				e.printStackTrace();
			}
			response.getWriter().println(result);
		} else if (operation.equals("update")) {
			int id = Integer.parseInt(request.getParameter("catid"));
			String name = request.getParameter("cname");
			JSONObject res = new JSONObject();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement stat = conn.createStatement();
				String query = "update expensecategory set cname='" + name + "' where catid=" + id;
				stat.execute(query);
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
				Class.forName("com.mysql.jdbc.Driver");
				Connection conne = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement state = conne.createStatement();
				String query = "delete from expensecategory where catid=" + cid;
				state.execute(query);
				ress.put("status", 1);

			} catch (Exception e) {
				ress.put("status", 0);
				e.printStackTrace();

			}
			response.getWriter().println(ress);

		} else if (operation.equals("getOne")) {
			int caid = Integer.parseInt(request.getParameter("catid"));
			JSONObject resu = new JSONObject();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connec = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement statem = connec.createStatement();
				String query = "select * from expensecategory where catid=" + caid;
				ResultSet rs = statem.executeQuery(query);
				if (rs.next()) {
					resu.put("catname", rs.getString(2));
				}

			} catch (Exception e) {
				resu.put("status", 0);
				e.printStackTrace();

			}
			response.getWriter().println(resu);

		} else if (operation.equals("getAll")) {
			JSONArray cat = new JSONArray();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement stateme = connect.createStatement();
				String query = "select * from expensecategory";
				ResultSet res = stateme.executeQuery(query);
				while (res.next()) {
					JSONObject get = new JSONObject();
					get.put("catid", res.getString(1));
					get.put("catname", res.getString(2));
					cat.put(get);
				}

			} catch (Exception e) {
				JSONObject error = new JSONObject();
				error.put("status", 0);
				cat.put(error);
				e.printStackTrace();
			}
			response.getWriter().println(cat);
		}

	}

}
