package com.smartglossa.bill;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class PurchaseMetaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PurchaseMetaServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");
        if (operation.equals("addItem")) {
            JSONObject obj = new JSONObject();
            int purchaseId = Integer.parseInt(request.getParameter("purchaseId"));
            int productId = Integer.parseInt(request.getParameter("productId"));
            float quantity = Float.parseFloat(request.getParameter("quantity"));
            float buyPrice = Float.parseFloat(request.getParameter("buyPrice"));
            float sellPrice = Float.parseFloat(request.getParameter("sellPrice"));
            try {
                purchaseMetaClass pur = new purchaseMetaClass();
                pur.addItem(purchaseId, productId,quantity, buyPrice, sellPrice);
                obj.put("status", "success");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.getWriter().print(obj);
        } else if (operation.equals("getAllItem")) {
            JSONArray array = new JSONArray();
            {
                try {
                    purchaseMetaClass pur = new purchaseMetaClass();
                    array = pur.getAll();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                response.getWriter().println(array);
            }
        } else if (operation.equals("delete")) {
            int purchaseLineId = Integer.parseInt(request.getParameter("purchaseLineId"));
            JSONObject obj = new JSONObject();
            try {
                purchaseMetaClass pur = new purchaseMetaClass();
                pur.delete(purchaseLineId);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.getWriter().println(obj);
        }else if(operation.equals("getId")){
        	JSONObject obj = new JSONObject();
        	int purchaseLineId = Integer.parseInt(request.getParameter("purchaseLineId"));
        			try {
        				purchaseMetaClass meta = new purchaseMetaClass();
        			obj=meta.getId(purchaseLineId);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
        			response.getWriter().println(obj);
        }
        else if(operation.equals("getOne")){
        	JSONObject object=new JSONObject();
        	int productId=Integer.parseInt(request.getParameter("productId"));
        	try {
				purchaseMetaClass res=new purchaseMetaClass();
				object=res.getOne(productId);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
        	response.getWriter().println(object);
        }
        }
  
}