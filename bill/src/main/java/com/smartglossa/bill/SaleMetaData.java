package com.smartglossa.bill;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class SaleMetaData extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public SaleMetaData() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation.equals("addSaleMetaData")) {
            JSONObject obj = new JSONObject();
            int saleId = Integer.parseInt(request.getParameter("saleid"));
            String billDate = request.getParameter("billdate");
            float vat = Float.parseFloat(request.getParameter("vat"));
            float discount = Float.parseFloat(request.getParameter("discount"));
            float billTotal = Float.parseFloat(request.getParameter("billtotal"));
            String payDate = request.getParameter("paydate");
            float paidAmount = Float.parseFloat(request.getParameter("paidAmount"));
            try {
                SaleMetaDataClass meta = new SaleMetaDataClass();
                meta.addSaleMetaData(saleId, billDate, vat, discount, billTotal, payDate, paidAmount);
                obj.put("status", "success");
            } catch (Exception e) {
                obj.put("status", "failure");
                e.printStackTrace();
            }
            response.getWriter().print(obj);
        } else if (operation.equals("getSaleMetaData")) {
            JSONArray result = new JSONArray();
            try {
                SaleMetaDataClass meta = new SaleMetaDataClass();
                result = meta.getSaleMetaData();
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.getWriter().print(result);
        } else if (operation.equals("getOneSaleMetaData")) {
            int saleId = Integer.parseInt(request.getParameter("saleid"));
            JSONObject obj = new JSONObject();
            try {
                SaleMetaDataClass meta = new SaleMetaDataClass();
                obj = meta.getOneSaleMetaData(saleId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.getWriter().print(obj);
        } else if (operation.equals("deleteSaleMetaData")) {
            int saleId = Integer.parseInt(request.getParameter("saleid"));
            JSONObject obj = new JSONObject();
            try {
                SaleMetaDataClass meta = new SaleMetaDataClass();
                meta.deleteSaleMetaData(saleId);
                obj.put("status", "Success");
            } catch (Exception e) {
                obj.put("status", "Failure");
                e.printStackTrace();
            }
            response.getWriter().print(obj);
        } else if (operation.equals("updateSaleMetaData")) {
            JSONObject obj = new JSONObject();
            int saleId = Integer.parseInt(request.getParameter("saleid"));
            String billDate = request.getParameter("billdate");
            float vat = Float.parseFloat(request.getParameter("vat"));
            float discount = Float.parseFloat(request.getParameter("discount"));
            float billTotal = Float.parseFloat(request.getParameter("billtotal"));
            try {
                SaleMetaDataClass meta = new SaleMetaDataClass();
                meta.updateSaleMetaData(saleId, billDate, vat, discount, billTotal);
                obj.put("status", "Success");
            } catch (Exception e) {
                obj.put("status", "Failure");
                e.printStackTrace();
            }
            response.getWriter().print(obj);
        }
    }
}
