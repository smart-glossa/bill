package com.smartglossa.bill;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.json.JSONArray;
import org.json.JSONObject;

public class BillApplication {
	String password = "root";
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	public void addProduct(int productId, String name, float cost) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", password);
		stmt = conn.createStatement();
		String query = "Insert into product value(" + productId + ", '" + name + "', " + cost + ")";
		stmt.execute(query);

	}

	public JSONObject getProduct(int pid) throws ClassNotFoundException, SQLException {
		JSONObject obj = new JSONObject();

		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", password);
		stmt = conn.createStatement();
		String query = "select * from product where productId = " + pid;
		rs = stmt.executeQuery(query);
		if (rs.next()) {
			obj.put("name", rs.getString(2));
			obj.put("cost", rs.getFloat(3));
		}
		return obj;
	}
	public void updateProduct(int productId, String name,float cost) throws ClassNotFoundException, SQLException{
		
		Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", password);
        stmt = conn.createStatement();
        String query =
                "Update product set name='" + name + "',cost= '" + cost + "'where productId= " + productId;
        stmt.execute(query);
	}
	public void deleteProduct(int productId) throws ClassNotFoundException, SQLException{
		 Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", password);
         stmt = conn.createStatement();
         String query = "Delete from product where productId= " + productId;
         stmt.execute(query);
	}
	public JSONArray getAllProduct() throws ClassNotFoundException, SQLException{
		JSONArray res = new JSONArray();
		Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", password);
        stmt = conn.createStatement();
        String query = "select * from product";
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            JSONObject obj = new JSONObject();
            obj.put("productId", rs.getInt(1));
            obj.put("name", rs.getString(2));
            obj.put("cost", rs.getFloat(3));
            res.put(obj);
        }
		return res;
		
	}
	public void addUser(String name, String uname, String pass, FileItem file) throws ClassNotFoundException, SQLException, IOException, FileUploadException{
          // Store image
          Class.forName("com.mysql.jdbc.Driver");
          conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", password);
          ps = conn.prepareStatement("insert into image(image,uname) values(?,?)");
          ps.setString(2, uname);
          // size must be converted to int otherwise it results in error
          ps.setBinaryStream(1, file.getInputStream(), (int) file.getSize());
          ps.executeUpdate();
          
          // Store user other details
          Class.forName("com.mysql.jdbc.Driver");
          stmt = conn.createStatement();
          String query =
                  "Insert into user(name,uname,pass) values('" + name + "', '" + uname + "', '" + pass + "')";
          stmt.execute(query);
	}
	public JSONObject login(String uname, String pass) throws ClassNotFoundException, SQLException{
		JSONObject obj = new JSONObject();
		 Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", password);
         stmt = conn.createStatement();
         String query = "select name from user where uname='" + uname + "'AND pass='" + pass + "'";
         rs = stmt.executeQuery(query);
         if (rs.next()) {
             obj.put("name", rs.getString(1));
             obj.put("Status", "success");
         }
		return obj;
		
	}
	public JSONObject getUserDetail(String uname) throws ClassNotFoundException, SQLException{
		JSONObject obj = new JSONObject();
		 Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", password);
         stmt = conn.createStatement();
         String query = "select name from user where uname='" + uname + "'";
         rs = stmt.executeQuery(query);
         if (rs.next()) {
             obj.put("name", rs.getString(1));
             obj.put("Status", "success");
         }
		return obj;
		
	}
	
}
