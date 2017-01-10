package com.smartglossa.bill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.jdbc.PreparedStatement;
 
public class SaleLineItemClass {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public SaleLineItemClass() throws ClassNotFoundException, SQLException {
        openConnection();
    }
    public void addSaleLine(int saleId, int productId, float quantity, float cost) throws SQLException {
        JSONObject obj = new JSONObject();
        try {
            String query = "insert into salelineitems(saleId,productId,quantity,cost)values(" + saleId + "," + productId
                    + "," + quantity + "," + cost + ")";
            stmt.execute(query);
            obj.put("status", "success");
        } finally {
            closeConnection();
        }
    }

    public JSONArray getSaleLineItem() throws SQLException {
        JSONArray result = new JSONArray();
        try {
            String query = "select *from salelineitems";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("saleLineId", rs.getInt("salelineid"));
                obj.put("saleId", rs.getInt("saleid"));
                obj.put("productId", rs.getInt("productid"));
                obj.put("quantity", rs.getFloat("quantity"));
                obj.put("cost", rs.getFloat("cost"));
                result.put(obj);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public JSONObject getOneSaleLineItem(int saleLineId) throws SQLException {
        JSONObject obj = new JSONObject();
        try {
            String query = "select * from salelineitems where saleLineId=" + saleLineId;
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                obj.put("saleId", rs.getInt("saleid"));
                obj.put("productId", rs.getInt("productid"));
                obj.put("quantity", rs.getFloat("quantity"));
                obj.put("cost", rs.getFloat("cost"));
                obj.put("saleLineId", rs.getInt("salelineid"));
            }
        } finally {
            closeConnection();
        }
        return obj;

    }
    public JSONObject getId( int saleLineId)throws SQLException {
    	JSONObject obj = new JSONObject();
    	try{
    	String query = "select * from salelineitems where salelineid="+ saleLineId;
    	 rs = stmt.executeQuery(query);
         if(rs.next()) {
        	  obj.put("saleLineId", rs.getInt("salelineid"));
        	 obj.put("saleId", rs.getInt("saleid"));
             obj.put("productId", rs.getInt("productid"));
             obj.put("quantity", rs.getFloat("quantity"));
             obj.put("cost", rs.getFloat("cost"));
    	}
    	}finally {
            closeConnection();
        }
        return obj;
    	
    }

    public void deleteSaleLineItem(int saleLineId) throws SQLException {
        try {
            String query = "Delete from salelineitems where saleLineId=" + saleLineId;
            stmt.execute(query);
        } finally {
            closeConnection();
        }
    }

    public void updateSaleLineItem(int saleLineId, int saleId, int productId, float quantity, float cost)
            throws SQLException {
        try {
            String query = "update salelineitems set saleId=" + saleId + ",productId=" + productId + ",quantity="
                    + quantity + ",cost=" + cost + " where saleLineId=" + saleLineId;
            stmt.execute(query);
        } finally {
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
