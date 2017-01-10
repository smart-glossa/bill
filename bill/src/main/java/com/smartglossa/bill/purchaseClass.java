package com.smartglossa.bill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

public class purchaseClass {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    public purchaseClass() throws ClassNotFoundException, SQLException {
        openConnection();
    }

    public void addPurchase(int purchaseId, String billDate, float vat, float discount, float billTotal, String payDate,
            float paidAmount) throws SQLException {
        try {
            String query = "insert into purchaseMetaData(purchaseId,billDate,vat,discount,billTotal) values("
                    + purchaseId + ",'" + billDate + "'," + vat + "," + discount + "," + billTotal + ")";
            stmt.execute(query);
            String query2 = "insert into purchasePayment(purchaseId,payDate,paidAmount) values(" + purchaseId + ","
                    + payDate + "," + paidAmount + ")";
            stmt.execute(query2);
        } finally {
            closeConnection();
        }
    }

    public JSONArray getAll() throws ClassNotFoundException, SQLException {
        JSONArray set = new JSONArray();
        try {

            String query = "select * from purchaseMetaData";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("purchaseId", rs.getInt("purchaseId"));
                obj.put("billDate", rs.getString("billDate"));
                obj.put("vat", rs.getString("vat"));
                obj.put("discount", rs.getString("discount"));
                obj.put("billTotal", rs.getInt("billTotal"));
                set.put(obj);
            }
        } finally {
            closeConnection();
        }
        return set;

    }

    public JSONObject getOne(int purchaseId) throws ClassNotFoundException, SQLException {
        JSONObject obj = new JSONObject();
        try {
            String query = "select * from purchaseMetaData where purchaseId=" + purchaseId;
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                obj.put("billDate", rs.getString("billDate"));
                obj.put("vat", rs.getString("vat"));
                obj.put("discount", rs.getString("discount"));
                obj.put("billTotal", rs.getString("billTotal"));
                obj.put("purchaseId", rs.getString("purchaseId"));
            }
        } finally {
            closeConnection();
        }
        return obj;

    }

    public void update(int purchaseId, String billDate, float vat, float discount, float billTotal)
            throws SQLException {
        try {
            String query = "update purchaseMetaData set billDate='" + billDate + "',vat=" + vat + ",discount="
                    + discount + ",billTotal=" + billTotal + " where purchaseId=" + purchaseId;
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