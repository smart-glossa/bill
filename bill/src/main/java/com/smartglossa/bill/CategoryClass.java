package com.smartglossa.bill;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import org.json.JSONObject;
import org.omg.CORBA.PUBLIC_MEMBER;

public class CategoryClass {
	 Connection conn = null;
	    Statement stat = null;
	    ResultSet rs = null;
	     public CategoryClass()throws ClassNotFoundException,SQLException {
	    	 openConnection();
			
		}
	     public void add( int cid,String cname)throws SQLException,ClassNotFoundException{ 
	     JSONObject obj = new JSONObject();
	    	String query="insert into expensecategory(catid,cname) values("+cid+",'"+cname+"')";
				stat.execute(query);
		 try {
						obj.put("status", "success");
			} finally{
				closeConnection();
			}
			
		}
	     public void update(int cid,String cname)throws SQLException,ClassNotFoundException {
	    	 JSONObject res=new JSONObject();
	    	 String query="update expensecategory set cname='"+cname+"'where catid="+cid;
				stat.execute(query);
	    	
	    	 try {
	    		 res.put("status", 1);
				
			} finally{
				closeConnection();
			}
	    	
	    	 
	     
	     };
	     private void openConnection() throws SQLException, ClassNotFoundException {
	         Class.forName("com.mysql.jdbc.Driver");
	         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill","root","root");
	                 
	         stat = conn.createStatement();
	     }

	     private void closeConnection() throws SQLException {
	         if (conn != null) {
	             conn.close();
	         }
	         if (stat != null) {
	             stat.close();
	         }
	        
	         if (rs != null) {
	             rs.close();
	         }
			
			
		}
		}


