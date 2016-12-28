package com.smartglossa.bill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;

public class ExpenseClass {
    Connection conn = null;
    Statement stat = null;
    ResultSet rs = null;

    public ExpenseClass() throws ClassNotFoundException, SQLException {
        openConnection();

    }

    public void add(int id, String date, String des, float amount) throws ClassNotFoundException, SQLException {
        JSONObject obj = new JSONObject();

        try {
            String query = "insert into expenses(catid,expDate,description,amount)values(" + id + ",'" + date + "','"
                    + des + "'," + amount + ")";
            stat.execute(query);
            obj.put("status", "success");
        } finally {

            closeConnection();
        }

    }

    public void expupdate(int id, String date, String des, float amount) throws ClassNotFoundException, SQLException {
        try {
            String query = "update expenses set expDate='" + date + "',description='" + des + "',amount=" + amount
                    + " where catid=" + id;
            stat.execute(query);
        } finally {
            closeConnection();
        }

    }

    public void expdelete(int expId) throws ClassNotFoundException, SQLException {
        try {
            String query = "delete from expenses where expId=" + expId;
            stat.execute(query);

        } finally {
            closeConnection();
        }

    }

    public JSONObject getOne(int expId) throws ClassNotFoundException, SQLException {
        JSONObject res = new JSONObject();
        try {

            String query = "select * from expenses where expId=" + expId;
            rs = stat.executeQuery(query);
            if (rs.next()) {
                res.put("catid", rs.getString("catid"));
                res.put("expDate", rs.getString("expDate"));
                res.put("description", rs.getString("description"));
                res.put("amount", rs.getString("amount"));

            }
        } finally {
            closeConnection();
        }
        return res;
    }

    public JSONArray getAll() throws ClassNotFoundException, SQLException {
        JSONArray arr = new JSONArray();
        try {
            String query = "select * from expenses";
            rs = stat.executeQuery(query);
            while (rs.next()) {
                JSONObject object = new JSONObject();
                object.put("catid", rs.getString("catid"));
                object.put("expDate", rs.getString("expDate"));
                object.put("description", rs.getString("description"));
                object.put("amount", rs.getString("amount"));
                arr.put(object);

            }

        } finally {
            closeConnection();

        }
        return arr;
    }

    public JSONObject expense(String date) throws ClassNotFoundException, SQLException {
        JSONObject object = new JSONObject();
        try {
            String query = "select * from expenses where expDate=" + date;
            rs = stat.executeQuery(query);
            if (rs.next()) {
                object.put("expId", rs.getString("expId"));
                object.put("catid", rs.getString("catid"));
                object.put("description", rs.getString("description"));
                object.put("amount", rs.getString("amount"));

            }

        } finally {
            closeConnection();
        }
        return object;
    }

    private void openConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
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
