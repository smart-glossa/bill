package com.smartglossa.bill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.jdbc.PreparedStatement;

public class SalePaymentClass {

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    public SalePaymentClass() throws ClassNotFoundException, SQLException {
        openConnection();
    }

    public void addSalePayment(int saleId, String payDate, float paidAmount) throws SQLException {
        JSONObject obj = new JSONObject();
        try {
            String query = "insert into salepayment(saleId,payDate,paidAmount)values(" + saleId + ",'" + payDate + "',"
                    + paidAmount + ")";
            stmt.execute(query);
            obj.put("status", "success");
        } finally {
            closeConnection();
        }
    }

    public JSONArray getSalePayment() throws SQLException {
        JSONArray result = new JSONArray();
        try {
            String query = "select *from salepayment";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("payId", rs.getInt("payid"));
                obj.put("saleId", rs.getInt("saleid"));
                obj.put("payDate", rs.getString("paydate"));
                obj.put("paidAmount", rs.getFloat("paidAmount"));
                result.put(obj);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public JSONObject getOneSalePayment(int payId) throws SQLException {
        JSONObject obj = new JSONObject();
        try {
            String query = "select * from salepayment where payId=" + payId;
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                obj.put("saleId", rs.getInt("saleid"));
                obj.put("payDate", rs.getString("paydate"));
                obj.put("paidAmount", rs.getFloat("paidamount"));
            }
        } finally {
            closeConnection();
        }
        return obj;

    }

    public void deleteSalePayment(int payId) throws SQLException {
        try {
            String query = "Delete from salepayment where payId=" + payId;
            stmt.execute(query);
        } finally {
            closeConnection();
        }
    }

    public void updateSalePayment(int payId, int saleId, String payDate, float paidAmount) throws SQLException {
        try {
            String query = "update salepayment set saleId=" + saleId + ",payDate='" + payDate + "',paidAmount="
                    + paidAmount + " where payId=" + payId;
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
