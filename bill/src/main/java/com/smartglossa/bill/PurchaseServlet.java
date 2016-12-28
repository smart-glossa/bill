package com.smartglossa.bill;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class PurchaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PurchaseServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation.equals("addPurchase")) {
            JSONObject obj = new JSONObject();
            int purchaseId = Integer.parseInt(request.getParameter("purchaseId"));
            String billDate = request.getParameter("billDate");
            float vat = Float.parseFloat(request.getParameter("vat"));
            float discount = Float.parseFloat(request.getParameter("discount"));
            float billTotal = Float.parseFloat(request.getParameter("billTotal"));
            String payDate = request.getParameter("payDate");
            float paidAmount = Float.parseFloat(request.getParameter("paidAmount"));

            try {
                purchaseClass pur = new purchaseClass();
                pur.addPurchase(purchaseId, billDate, vat, discount, billTotal, payDate, paidAmount);
                obj.put("status", 1);
            } catch (Exception e) {
                obj.put("status", 0);
                e.printStackTrace();
            }
            response.getWriter().print(obj);
        } else if (operation.equals("getAll")) {
            JSONArray set = new JSONArray();
            try {
                purchaseClass pur = new purchaseClass();
                set = pur.getAll();
            } catch (Exception e) {
                JSONObject obj = new JSONObject();
                obj.put("status", 0);
                set.put(obj);
                e.printStackTrace();
            }
            response.getWriter().println(set);
        } else if (operation.equals("getOne")) {
            int purchaseId = Integer.parseInt(request.getParameter("purchaseId"));
            JSONObject obj = new JSONObject();
            try {
                purchaseClass pur = new purchaseClass();
                obj = pur.getOne(purchaseId);
            } catch (Exception e) {
                obj.put("status", 0);
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.getWriter().println(obj);
        } else if (operation.equals("update")) {
            JSONObject obj = new JSONObject();
            int purchaseId = Integer.parseInt(request.getParameter("purchaseId"));
            String billDate = request.getParameter("billDate");
            float vat = Float.parseFloat(request.getParameter("vat"));
            float discount = Float.parseFloat(request.getParameter("discount"));
            float billTotal = Float.parseFloat(request.getParameter("billTotal"));
            try {
                purchaseClass pur = new purchaseClass();
                pur.update(purchaseId, billDate, vat, discount, billTotal);
                obj.put("status", "success");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            response.getWriter().println(obj);
        }

    }
}