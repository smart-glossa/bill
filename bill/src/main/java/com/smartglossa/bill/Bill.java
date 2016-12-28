package com.smartglossa.bill;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONObject;

public class Bill extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        String op = request.getParameter("operation");
        if (op.equals("addProduct")) {
            int productId = Integer.parseInt(request.getParameter("pId"));
            String pName = request.getParameter("pName");
            float sellPrice = Float.parseFloat(request.getParameter("sellPrice"));
            float buyPrice = Float.parseFloat(request.getParameter("buyPrice"));
            float quantity = Float.parseFloat(request.getParameter("quantity"));
            JSONObject obj = new JSONObject();
            try {
                List<FileItem> items = sfu.parseRequest(request);
                FileItem file = (FileItem) items.get(0);
                BillApplication bill = new BillApplication();
                bill.addProduct(productId, pName, sellPrice, buyPrice, quantity, file);
                obj.put("status", 1);
            } catch (Exception e) {
                e.printStackTrace();
                obj.put("status", 0);
                obj.put("message", e.getMessage());
            }
            response.getWriter().print(obj);
        } else if (op.equals("getProduct")) {
            int pid = Integer.parseInt(request.getParameter("pId"));
            JSONObject obj = new JSONObject();
            try {
                BillApplication bill = new BillApplication();
                JSONObject product = bill.getProduct(pid);
                obj.put("status", 1);
                obj.put("product", product);
            } catch (Exception e) {
                e.printStackTrace();
                obj.put("status", 0);
                obj.put("message", e.getMessage());
            }
            response.getWriter().print(obj);
        } else if (op.equals("updateProduct")) {
            int productId = Integer.parseInt(request.getParameter("pId"));
            String pName = request.getParameter("pName");
            float sellPrice = Float.parseFloat(request.getParameter("sellPrie"));
            float buyPrice = Float.parseFloat(request.getParameter("buyPrice"));
            float quantity = Float.parseFloat(request.getParameter("quantity"));
            JSONObject obj = new JSONObject();
            try {
                List<FileItem> items = sfu.parseRequest(request);
                FileItem file = (FileItem) items.get(0);
                BillApplication bill = new BillApplication();
                bill.updateProduct(productId, pName, buyPrice, sellPrice, quantity, file);
                obj.put("status", 1);
            } catch (Exception e) {
                e.printStackTrace();
                obj.put("status", 0);
                obj.put("message", e.getMessage());
            }
            response.getWriter().print(obj);
        } else if (op.equals("deleteProduct")) {
            int productId = Integer.parseInt(request.getParameter("pId"));
            JSONObject obj = new JSONObject();
            try {
                BillApplication bill = new BillApplication();
                bill.deleteProduct(productId);
                obj.put("status", 1);
            } catch (Exception e) {
                e.printStackTrace();
                obj.put("status", 0);
                obj.put("message", e.getMessage());
            }
            response.getWriter().print(obj);
        } else if (op.equals("getAllProduct")) {
            JSONObject obj = new JSONObject();
            try {
                BillApplication bill = new BillApplication();
                JSONArray products = bill.getAllProduct();
                obj.put("status", 1);
                obj.put("products", products);
            } catch (Exception e) {
                e.printStackTrace();
                obj.put("status", 0);
                obj.put("message", e.getMessage());
            }
            response.getWriter().print(obj);
        } else if (op.equals("addUser")) {
            String name = request.getParameter("name");
            String uname = request.getParameter("uname");
            String pass = request.getParameter("pass");

            JSONObject obj = new JSONObject();
            try {
                List<FileItem> items = sfu.parseRequest(request);
                FileItem file = (FileItem) items.get(0);
                BillApplication bill = new BillApplication();
                bill.addUser(name, uname, pass, file);
                obj.put("status", 1);
            } catch (Exception e) {
                e.printStackTrace();
                obj.put("status", 0);
                obj.put("message", e.getMessage());
            }
            response.getWriter().print(obj);
        } else if (op.equals("login")) {
            String uname = request.getParameter("user");
            String pass = request.getParameter("passw");

            JSONObject obj = new JSONObject();
            try {
                BillApplication bill = new BillApplication();
                JSONObject result = bill.login(uname, pass);
                obj.put("status", 1);
                obj.put("message", result);
            } catch (Exception e) {
                e.printStackTrace();
                obj.put("status", 0);
                obj.put("message", e.getMessage());
            }
            response.getWriter().print(obj);
        } else if (op.equals("getUserDetail")) {
            String uname = request.getParameter("uname");

            JSONObject obj = new JSONObject();
            try {
                BillApplication bill = new BillApplication();
                JSONObject result = bill.getUserDetail(uname);
                obj.put("status", 1);
                obj.put("message", result);
            } catch (Exception e) {
                e.printStackTrace();
                obj.put("status", 0);
                obj.put("message", e.getMessage());
            }
            response.getWriter().print(obj);
        } else if (op.equals("getProductImage")) {
            int pid = Integer.parseInt(request.getParameter("productId"));

            try {
                BillApplication bill = new BillApplication();
                Blob b = bill.getProductImage(pid);
                if (b != null) {
                    response.setContentType("image/png;base64;");
                    response.setContentLength((int) b.length());
                    InputStream is = b.getBinaryStream();
                    OutputStream os = response.getOutputStream();
                    byte buf[] = new byte[(int) b.length()];
                    byte[] result = Base64.encodeBase64(buf);
                    is.read(result);
                    os.write(result);
                    os.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (op.equals("getProfilePicture")) {
            String uname = request.getParameter("uname");

            try {
                BillApplication bill = new BillApplication();
                Blob b = bill.getProfilePicture(uname);
                if (b != null) {
                    response.setContentType("image/png;base64;");
                    response.setContentLength((int) b.length());
                    InputStream is = b.getBinaryStream();
                    OutputStream os = response.getOutputStream();
                    byte buf[] = new byte[(int) b.length()];
                    byte[] result = Base64.encodeBase64(buf);
                    is.read(result);
                    os.write(result);
                    os.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (op.equals("updateProfile")) {
            String uname = request.getParameter("uname");

            JSONObject obj = new JSONObject();
            try {
                List<FileItem> items = sfu.parseRequest(request);
                FileItem file = (FileItem) items.get(0);
                BillApplication object = new BillApplication();
                object.updateProfile(uname, file);
                obj.put("status", 1);
            } catch (Exception ex) {
                ex.printStackTrace();
                obj.put("status", 0);
                obj.put("message", ex.getMessage());
            }
            response.getWriter().print(obj);

        }

    }

}
