package com.smartglossa.bill;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class PurchasePaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PurchasePaymentServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation.equals("addPayment")) {
            JSONObject obj = new JSONObject();
            int purchaseId = Integer.parseInt(request.getParameter("purchaseId"));
            String payDate = request.getParameter("payDate");
            float paidAmount = Float.parseFloat(request.getParameter("paidAmount"));
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
                Statement stmt = conn.createStatement();
                String query = "insert into purchasePayment(purchaseId,payDate,paidAmount) values(" + purchaseId + ","
                        + payDate + "," + paidAmount + ")";
                stmt.execute(query);
                obj.put("status", "success");
            } catch (Exception e) {
                obj.put("status", "failure");
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.getWriter().println(obj);
        } else if (operation.equals("getAll")) {
            JSONArray array = new JSONArray();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
                Statement stmt = conn.createStatement();
                String query = "select * from purchasePayment";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    JSONObject obj = new JSONObject();
                    obj.put("purchaseId", rs.getInt("purchaseId"));
                    obj.put("payDate", rs.getString("payDate"));
                    obj.put("paidAmount", rs.getString("paidAmount"));
                    array.put(obj);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.getWriter().println(array);
        } else if (operation.equals("getOne")) {
            int payId = Integer.parseInt(request.getParameter("payId"));
            JSONObject obj = new JSONObject();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
                Statement stmt = conn.createStatement();
                String query = "select * from purchasePayment where payId=" + payId;
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    obj.put("purchaseId", rs.getString("purchaseId"));
                    obj.put("payDate", rs.getString("payDate"));
                    obj.put("paidAmount", rs.getString("paidAmount"));
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.getWriter().println(obj);
        }
    }
}
