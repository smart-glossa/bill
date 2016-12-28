
package com.smartglossa.bill;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation.equals("cusAdd")) {
            int cid = Integer.parseInt(request.getParameter("cid"));
            String cname = request.getParameter("cname");
            String caddr = request.getParameter("caddr");
            String cphno = request.getParameter("cphno");
            JSONObject result = new JSONObject();
            try {
                CustomerClass cus = new CustomerClass();
                cus.cusAdd(cid, cname, caddr, cphno);
                result.put("status", 1);
            } catch (Exception e) {
                result.put("status", 0);
                e.printStackTrace();
            }
            response.getWriter().print(result);
        } else if (operation.equals("cusUpdate")) {
            int cid = Integer.parseInt(request.getParameter("cid"));
            String cname = request.getParameter("cname");
            String caddr = request.getParameter("caddr");
            String cphno = request.getParameter("cphno");
            JSONObject result = new JSONObject();
            try {
                CustomerClass cus = new CustomerClass();
                cus.cusUpdate(cid, cname, caddr, cphno);
                result.put("status", 1);
            } catch (Exception e) {
                result.put("status", 0);
                e.printStackTrace();
            }
            response.getWriter().print(result);
        } else if (operation.equals("cusDelete")) {
            int cid = Integer.parseInt(request.getParameter("cid"));
            JSONObject result = new JSONObject();
            try {
                CustomerClass cus = new CustomerClass();
                cus.cusDelete(cid);
                result.put("status", 1);
            } catch (Exception e) {
                result.put("status", 0);
                e.printStackTrace();
            }
            response.getWriter().print(result);
        } else if (operation.equals("cusOne")) {
            int id = Integer.parseInt(request.getParameter("id"));
            JSONObject result = new JSONObject();
            try {
                CustomerClass cus = new CustomerClass();
                result = cus.cusOne(id);
            } catch (Exception e) {
                result.put("status", 0);
                e.printStackTrace();
            }
            response.getWriter().print(result);
        } else if (operation.equals("cusAll")) {
            JSONArray result = new JSONArray();
            try {
                CustomerClass cus = new CustomerClass();
                result = cus.cusAll();
            } catch (Exception e) {
                JSONObject get = new JSONObject();
                get.put("status", 0);
                result.put(get);
                e.printStackTrace();
            }
            response.getWriter().print(result);
        } else if (operation.equals("cusale")) {
            int cuid = Integer.parseInt(request.getParameter("cId"));
            JSONArray result = new JSONArray();
            try {
                CustomerClass cus = new CustomerClass();
                result = cus.cusale(cuid);
            } catch (Exception e) {
                JSONObject obj = new JSONObject();
                obj.put("status", "0");
                result.put(obj);
                e.printStackTrace();
            }
            response.getWriter().print(result);
        }
    }
}