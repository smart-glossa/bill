package com.smartglossa.bill;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

public class CustomerClass {
	Connection con = null;
	Statement stat = null;
	ResultSet rs = null;

	public CustomerClass() throws ClassNotFoundException, SQLException {
		openConnection();

	}

	public void cusAdd(int cid, String cname, String caddr, int cphno)
			throws ClassNotFoundException, SQLException {
		try {
			String query = "insert into customer(customerId,name,address,phonenumber)values(" + cid + ",'" + cname
					+ "','" + caddr + "'," + cphno + ")";
			stat.execute(query);
		} finally {
			closeConnection();
		}

	}

	public void cusUpdate(int cid, String cname, String caddr, int cphno)
			throws ClassNotFoundException, SQLException {
		try {
			String query = "update customer set name=' " + cname + " ',address=' " + caddr + " ',phonenumber=" + cphno
					+ " where customerId=" + cid;
			stat.execute(query);

		} finally {
			closeConnection();

		}

	}

	public void cusDelete(int cid) throws ClassNotFoundException, SQLException {
		try {
			String query = "delete from customer where customerId=" + cid;
			stat.execute(query);
		} finally {
			closeConnection();
		}

	}

	public JSONObject cusOne(int id) throws ClassNotFoundException, SQLException {
		JSONObject result = new JSONObject();
		try {
			String query = "select * from customer where customerId=" + id;
			ResultSet rs = stat.executeQuery(query);
			if (rs.next()) {
				result.put("name", rs.getString(2));
				result.put("address", rs.getString(3));
				result.put("phonenumber", rs.getInt(4));

			}
		} finally {
			closeConnection();
		}

		return null;

	}
	public JSONArray cusAll() throws ClassNotFoundException,SQLException {
		JSONArray result=new JSONArray();
		try{
			String query = "select * from customer";
			ResultSet res = stat.executeQuery(query);
			while (res.next()) {
				JSONObject get = new JSONObject();
				get.put("name", res.getString(2));
				get.put("address", res.getString(3));
				get.put("phonenumber", res.getInt(4));
				result.put(get);
			}

		}
		finally{
			closeConnection();
			
		}
		return result;
		
	}
	private void openConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://" + BillConstants.MYSQL_SERVER + "/" + BillConstants.DATABASE
				+ BillConstants.USERNAME + BillConstants.PASSWORD);
		stat = con.createStatement();
	}

	private void closeConnection() throws SQLException {
		if (con != null) {
			con.close();
		}
		if (stat != null) {
			stat.close();
		}
		if (rs != null) {
			rs.close();
		}

	}

}
