package com.smartglossa.bill;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class ExpensesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation.equals("add")) {
            JSONObject obj = new JSONObject();
            int id = Integer.parseInt(request.getParameter("catid"));
            String date = request.getParameter("expDate");
            String des = request.getParameter("description");
            float amount = Float.parseFloat(request.getParameter("amount"));
            try {
                ExpenseClass exp = new ExpenseClass();
                exp.add(id, date, des, amount);
                obj.put("status", 1);
            } catch (Exception e) {
                obj.put("status", 0);
                e.printStackTrace();

            }
            response.getWriter().println(obj);

        } else if (operation.equals("expupdate")) {
            int id = Integer.parseInt(request.getParameter("catid"));
            String date = request.getParameter("expDate");
            String des = request.getParameter("description");
            float amount = Float.parseFloat(request.getParameter("amount"));
            JSONObject object = new JSONObject();
            try {
                ExpenseClass exp = new ExpenseClass();
                exp.expupdate(id, date, des, amount);
                object.put("status", 1);
            } catch (Exception e) {
                object.put("status", 0);
                e.printStackTrace();

            }
            response.getWriter().println(object);

        } else if (operation.equals("expdelete")) {
            int expId = Integer.parseInt(request.getParameter("expId"));
            JSONObject set = new JSONObject();
            try {
                ExpenseClass cla = new ExpenseClass();
                cla.expdelete(expId);
                set.put("status", 1);
            } catch (Exception e) {
                set.put("status", 0);
                e.printStackTrace();

            }
            response.getWriter().println(set);

        } else if (operation.equals("getOne")) {
            int expId = Integer.parseInt(request.getParameter("expId"));
            JSONObject res = new JSONObject();
            try {
                ExpenseClass expense = new ExpenseClass();
                res = expense.getOne(expId);

            } catch (Exception e) {
                res.put("status", 0);
                e.printStackTrace();
            }
            response.getWriter().println(res);

        } else if (operation.equals("getAll")) {
            JSONArray arr = new JSONArray();
            try {

                ExpenseClass expc = new ExpenseClass();
                arr = expc.getAll();

            } catch (Exception e) {
                JSONObject error = new JSONObject();
                error.put("status", 0);
                arr.put(error);
                e.printStackTrace();
            }
            response.getWriter().println(arr);
        } else if (operation.equals("expense")) {
            String date = request.getParameter("expDate");
            JSONObject object = new JSONObject();
            try {
                ExpenseClass expensec = new ExpenseClass();
                object = expensec.expense(date);

            } catch (Exception e) {
                e.printStackTrace();

            }
            response.getWriter().print(object);
        }

    }
}
