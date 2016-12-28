package com.smartglossa.bill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

public class purchaseMetaClass {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public purchaseMetaClass() throws SQLException, ClassNotFoundException {
        openConnection();

    }

    public void addItem(int purchaseId, int productId, float quantity, float buyPrice, float sellPrice)
            throws SQLException {
        JSONObject obj = new JSONObject();
        try {
            String query = "insert into purchaseLineItem(purchaseId,productId,quantity,buyPrice,sellPrice) values("
                    + purchaseId + "," + productId + "," + quantity + "," + buyPrice + "," + sellPrice + ")";
            stmt.execute(query);
            obj.put("status", "success");
        } finally {
            closeConnection();
        }

    }

    public JSONArray getAll() throws SQLException, ClassNotFoundException {
        JSONArray set = new JSONArray();
        try {
            String query = "select * from purchaseLineItem";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                JSONObject object = new JSONObject();
                object.put("purchaseId", rs.getInt("purchaseId"));
                object.put("productId", rs.getInt("productId"));
                object.put("quantity", rs.getFloat("quantity"));
                object.put("buyprice", rs.getFloat("buyPrice"));
                object.put("sellPrice", rs.getFloat("sellPrice"));
                set.put(object);
            }

        } finally {
            closeConnection();
        }
        return set;
    }

    public void delete(int purchaseLineId) throws SQLException, ClassNotFoundException {
        JSONObject obj = new JSONObject();
        try {
            String query = "delete from purchaseLineItem where purchaseLineId=" + purchaseLineId;
            stmt.execute(query);
            obj.put("status", "success");
        } finally {
            // TODO: handle finally clause
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
        if (rs != null) {
            rs.close();
        }
    }
}