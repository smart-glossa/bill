package com.smartglossa.bill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.jdbc.PreparedStatement;

public class SaleMetaDataClass {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	public SaleMetaDataClass() throws ClassNotFoundException, SQLException {
		openConnection();
	}
	public void addSaleMetaData(int saleId,String billDate, float vat, float discount,float billTotal)throws SQLException{
	JSONObject obj = new JSONObject();
	try{
		String query = "insert into salemetadata(saleId,billDate,vat,discount,billTotal)values("+ saleId +",'"+ billDate +"',"+ vat +","+ discount +","+ billTotal +")";
		stmt.execute(query);
		obj.put("status","success");
	}finally{
		closeConnection();
	}
	}
	public JSONArray getSaleMetaData() throws SQLException {
		JSONArray result = new JSONArray();
		try {
			String query = "select *from salemetadata";
             rs = stmt.executeQuery(query);
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("saleId", rs.getInt("saleid"));
                obj.put("billDate", rs.getString("billdate"));
                obj.put("vat", rs.getFloat("vat"));
                obj.put("discount",rs.getFloat("discount"));
                obj.put("billTotal", rs.getFloat("billtotal"));
                result.put(obj);
            }
		}finally {
			closeConnection();
		}
		return result;
	}
	public JSONObject getOneSaleMetaData(int saleId) throws SQLException {
		JSONObject obj = new JSONObject();
		try {
			 String query = "select * from salemetadata where saleId="+ saleId;
	          rs = stmt.executeQuery(query);
	          if (rs.next()) {
	        	  obj.put("billDate", rs.getString("billdate"));
	                obj.put("vat", rs.getFloat("vat"));
	                obj.put("discount",rs.getFloat("discount"));
	                obj.put("billTotal", rs.getFloat("billtotal"));
	          }
		}finally {
	  			closeConnection();
	  		}
	  		return obj;
	}
	public void deleteSaleMetaData(int saleId) throws SQLException{
		try{
			 String query = "Delete from salemetadata where saleId=" + saleId;
             stmt.execute(query);
		}finally{
			closeConnection();
		}
	}
	public void updateSaleMetaData(int saleId,String billDate, float vat, float discount,float billTotal) throws  SQLException{
		try{
			String query = "update salemetadata set billDate='"+ billDate +"',vat=" + vat +",discount="+ discount +",billTotal="+ billTotal+" where saleId=" +saleId ;
		     stmt.execute(query);
		}finally{
			closeConnection();
		}
	}
	private void openConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		  conn = DriverManager.getConnection("jdbc:mysql://" + BillConstants.MYSQL_SERVER + "/" + BillConstants.DATABASE,
	                BillConstants.USERNAME, BillConstants.PASSWORD);
		stmt = conn.createStatement();
	}
	
	private void closeConnection() throws SQLException {
		if (conn != null) {
			conn.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (rs != null) {
			rs.close();
		}
	}
}
