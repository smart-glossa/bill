package com.smartglossa.bill;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

public class dealerClass {
	 Connection conn = null;
	 Statement stmt = null;
	 ResultSet  rs = null;
	private JSONObject one;


public dealerClass() throws ClassNotFoundException, SQLException {
    openConnection();
    
}
public void adddealer(int dealerId, String name, String address, String phoneNumber, String TINNumber)
        throws SQLException, ClassNotFoundException {
	JSONObject objec = new JSONObject();
	try {
		String query = "insert into dealer(dealerId,name,address,phoneNumber,TINNumber)values(" + dealerId
				+ ",'" + name + "','" + address + "','" + phoneNumber + "','" + TINNumber + "')";
		stmt.execute(query);
		objec.put("status", "1");
	} finally {
	  closeConnection();
	}
}
public  JSONArray getall() throws SQLException, ClassNotFoundException {
	JSONArray result = new JSONArray();
	try  {
		String query = "select * from dealer";
		rs = stmt.executeQuery(query);                                
		while(rs.next()){
			JSONObject get = new JSONObject();
			get.put("dealerId", rs.getInt("dealerId"));
			get.put("name", rs.getString("name"));
			get.put("address", rs.getString("address"));
			get.put("phoneNumber", rs.getString("phoneNumber"));
			get.put("TINNumber", rs.getString("TINNumber"));
			result.put(get);
		}
		return result;
	} finally {
	   closeConnection();
	}
}
public  JSONObject getone(int dealerId) throws SQLException, ClassNotFoundException {
	JSONObject one = new JSONObject();
	try {
		String query = "select * from dealer where dealerId="+ dealerId;
		rs = stmt.executeQuery(query);
		if(rs.next()){
			one.put("name", rs.getString("name"));
			one.put("address", rs.getString("address"));
			one.put("phoneNumber", rs.getString("phoneNumber"));
			one.put("TINNumber", rs.getString("TINNumber"));
		}
	
	} finally {
		closeConnection();
	}
	return one;

}
public void updatedealer(int dealerId, String name, String address, String phoneNumber, String TINNumber)
        throws SQLException, ClassNotFoundException {
	JSONObject update = new JSONObject();
	try {
		String query = "update dealer set name='" + name + "',address='" + address + "',phoneNumber='"
				+ phoneNumber + "',TINNumber='" + TINNumber + "' where dealerId=" + dealerId;
		stmt.execute(query);
		update.put("status", "1");
	
	} finally {
		
		closeConnection();
	}
}
public void deletedealer(int dealerId) throws SQLException, ClassNotFoundException {
	JSONObject delete = new JSONObject();
	try {
		String query = "delete from dealer where dealerId=" + dealerId;
		stmt.execute(query);
		delete.put("status", "1");
	} finally {
		closeConnection();
	}
}
public  JSONArray dealerbill(int dId) throws SQLException, ClassNotFoundException {
	JSONArray bill =  new JSONArray();
	try {
		String query = "select purchaseId from dealerBill where dealerId=" + dId;
		rs = stmt.executeQuery(query);
		while (rs.next()) {
			int pId = rs.getInt("purchaseId");
			String queryy = "select * from purchasemetadata,purchaselineitem,purchasepayment where purchasemetadata.purchaseId="+ pId + " AND purchaselineitem.purchaseId=" + pId + " AND purchasepayment.purchaseId="+ pId;
		    rs = stmt.executeQuery(queryy);
		    while (rs.next()) {
				JSONObject all = new JSONObject();
				all.put("billDate", rs.getDate("billDate"));
				all.put("vat", rs.getFloat("vat"));
				all.put("discount", rs.getFloat("discount"));
				all.put("billTotal", rs.getFloat("billTotal"));
				all.put("productId", rs.getInt("productId"));
				all.put("purchaseLineId", rs.getInt("purchaseLineId"));
				all.put("quantity", rs.getFloat("quantity"));
				all.put("payId", rs.getInt("payId"));
				all.put("payDate", rs.getDate("payDate"));
				all.put("paidAmount", rs.getFloat("paidAmount"));
				bill.put(all);
		}
		}
	}
		
		
	 finally {
		 closeConnection();
	}
	return bill;
}



private void openConnection() throws SQLException, ClassNotFoundException {
	 Class.forName("com.mysql.jdbc.Driver");
     conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill","root","root");
     stmt = conn.createStatement();
	
}
	
private void closeConnection() throws SQLException {
	if(conn != null){
		conn.close();
	
      }
     if(stmt != null){
    	 stmt.close();
}
     if(rs != null){
    	 rs.close();
     }
    
    


}
}




