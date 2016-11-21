package com.smartglossa.bill;

import java.io.IOException;
import java.sql.Blob;
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
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	public BillApplication() throws ClassNotFoundException, SQLException{
	    Class.forName("com.mysql.jdbc.Driver");
	    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "");
	    stmt = conn.createStatement();
	}
	
	public void addProduct(int productId, String name, float cost, FileItem file) throws SQLException, ClassNotFoundException, IOException {
	    
       
        String query = "Insert into product value(" + productId + ", '" + name + "', " + cost + ")";
        stmt.execute(query);
	    
        ps = conn.prepareStatement("insert into productImage(pimage,pid) values(?,?)");
        ps.setInt(2, productId);
        // size must be converted to int otherwise it results in error
        ps.setBinaryStream(1, file.getInputStream(), (int) file.getSize());
        ps.executeUpdate();
	    

	}

	public JSONObject getProduct(int pid) throws ClassNotFoundException, SQLException {
		JSONObject obj = new JSONObject();

		String query = "select * from product where productId = " + pid;
		rs = stmt.executeQuery(query);
		if (rs.next()) {
			obj.put("name", rs.getString(2));
			obj.put("cost", rs.getFloat(3));
		}
		return obj;
	}
	public void updateProduct(int productId, String name,float cost, FileItem file) throws ClassNotFoundException, SQLException, IOException{
		
       
        String query =
                "Update product set name='" + name + "',cost= '" + cost + "'where productId= " + productId;
        stmt.execute(query);
        
        ps = conn.prepareStatement("Insert into productImage(pimage,pid) values(?,?) ON DUPLICATE KEY update pimage= ? , pid= ?");
        ps.setInt(2, productId);
        // size must be converted to int otherwise it results in error
        ps.setBinaryStream(1, file.getInputStream(), (int) file.getSize());
        ps.setInt(4, productId);
        // size must be converted to int otherwise it results in error
        ps.setBinaryStream(3, file.getInputStream(), (int) file.getSize());
        ps.executeUpdate();
	}
	public void deleteProduct(int productId) throws ClassNotFoundException, SQLException{
        
         String query = "Delete from product where productId= " + productId;
         stmt.execute(query);
	}
	public JSONArray getAllProduct() throws ClassNotFoundException, SQLException{
		JSONArray res = new JSONArray();
       
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
          ps = conn.prepareStatement("insert into image(image,uname) values(?,?)");
          ps.setString(2, uname);
          // size must be converted to int otherwise it results in error
          ps.setBinaryStream(1, file.getInputStream(), (int) file.getSize());
          ps.executeUpdate();
          
          // Store user other details
         
          String query =
                  "Insert into user(name,uname,pass) values('" + name + "', '" + uname + "', '" + pass + "')";
          stmt.execute(query);
	}
	
	public void updateProfile(String uname, FileItem file) throws ClassNotFoundException, SQLException, IOException {
        ps = conn.prepareStatement("Update image set image = ? where uname = ?");
        ps.setBinaryStream(1, file.getInputStream(), (int) file.getSize());
        ps.setString(2, uname);
        ps.executeUpdate();
	}
	public JSONObject login(String uname, String pass) throws ClassNotFoundException, SQLException{
		JSONObject obj = new JSONObject();
        
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
        
         String query = "select name from user where uname='" + uname + "'";
         rs = stmt.executeQuery(query);
         if (rs.next()) {
             obj.put("name", rs.getString(1));
             obj.put("Status", "success");
         }
		return obj;
		
	}
	public Blob getProfilePicture(String uname) throws ClassNotFoundException, SQLException{
       
        String query = "select image from image where uname='" + uname + "'";
        rs = stmt.executeQuery(query);
        Blob b = null;
        if(rs.next()) {
           b = rs.getBlob("image");
        }
        return b;
	    
	}
	public Blob getProductImage(int pid) throws ClassNotFoundException, SQLException{
       
        String query = "select pimage from productImage where pid=" + pid;
        rs = stmt.executeQuery(query);
        Blob b = null;
        if (rs.next()) {
            b = rs.getBlob("pimage");
        }
        
        return b;
	}
	
}
