package com.smartglossa.bill;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class ExpensesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		if (operation.equals("add")) {
			int id = Integer.parseInt(request.getParameter("catid"));
			String date = request.getParameter("expDate");
			String des = request.getParameter("description");
			float amount = Float.parseFloat(request.getParameter("amount"));
			JSONObject re = new JSONObject();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement stat = con.createStatement();
				String query = "insert into expenses (catid,expDate,description,amount) values(" + id + ",'" + date
						+ "','" + des + "'," + amount + ")";
				stat.execute(query);
				re.put("status", 1);

			} catch (Exception e) {
				re.put("status", 0);
				e.printStackTrace();

			}
			response.getWriter().println(re);

		} else if (operation.equals("expupdate")) {
			int cid = Integer.parseInt(request.getParameter("catid"));
			String edate = request.getParameter("expDate");
			String desr = request.getParameter("description");
			float amou = Float.parseFloat(request.getParameter("amount"));
			JSONObject res = new JSONObject();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement state = conn.createStatement();
				String query = "update expenses set expDate='" + edate + "', description='" + desr + "', amount=" + amou
						+ " where catid=" + cid;
				state.execute(query);
				res.put("status", 1);
			} catch (Exception e) {
				res.put("status", 0);
				e.printStackTrace();

			}
			response.getWriter().println(res);

		} else if (operation.equals("expdelete")) {
			int expId = Integer.parseInt(request.getParameter("expId"));
			JSONObject resu = new JSONObject();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conne = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement statem = conne.createStatement();
				String query = "delete from expenses where expId=" + expId;
				statem.execute(query);
				resu.put("status", 1);
			} catch (Exception e) {
				resu.put("status", 0);
				e.printStackTrace();

			}
			response.getWriter().println(resu);

		} else if (operation.equals("getOne")) {
			int expId = Integer.parseInt(request.getParameter("expId"));
			JSONObject resul = new JSONObject();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connec = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement stateme = connec.createStatement();
				String query = "select * from expenses where expId=" + expId;
				ResultSet rs = stateme.executeQuery(query);

				if (rs.next()) {
					resul.put("expId", rs.getString("expId"));
					resul.put("catid", rs.getString("catid"));
					resul.put("expDate", rs.getString("expDate"));
					resul.put("description", rs.getString("description"));
					resul.put("amount", rs.getString("amount"));

				}
			} catch (Exception e) {
				resul.put("status", 0);
				e.printStackTrace();
			}
			response.getWriter().println(resul);

		} else if (operation.equals("getAll")) {
			JSONArray exp = new JSONArray();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
				Statement statement = connection.createStatement();
				String query = "select * from expenses";
				ResultSet re = statement.executeQuery(query);
				while (re.next()) {
					JSONObject get = new JSONObject();
					get.put("expId", re.getString("expId"));
					get.put("catid", re.getString("catid"));
					get.put("expDate", re.getString("expDate"));
					get.put("description", re.getString("description"));
					get.put("amount", re.getString("amount"));
					exp.put(get);
				}

			} catch (Exception e) {
				JSONObject error = new JSONObject();
				error.put("status", 0);
				exp.put(error);
				e.printStackTrace();
			}
			response.getWriter().println(exp);
		} else if(operation.equals("expense")){
			String date=request.getParameter("expDate");
        	JSONObject obj=new JSONObject();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/bill","root","root");
				Statement state=connection.createStatement();
				String queryy="select * from expenses where expDate="+date;
				ResultSet res=state.executeQuery(queryy);
                if(res.next()){
                	obj.put("catid",res.getString("catid"));
                	obj.put("description",res.getString("description"));
                	obj.put("amount",res.getString("amount"));
                }
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			response.getWriter().print(obj);
		}
		
	}
}
