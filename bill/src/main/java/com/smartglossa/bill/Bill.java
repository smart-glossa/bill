package com.smartglossa.bill;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        String password = "root";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            if (op.equals("addProduct")) {
                int productId = Integer.parseInt(request.getParameter("pid"));
                String name = request.getParameter("pname");
                float cost = Float.parseFloat(request.getParameter("cost"));

                // Add Product
                JSONObject obj = new JSONObject();
                try {
                	BillApplication bill = new BillApplication();
                	bill.addProduct(productId, name, cost);
                } catch (Exception e) {
                    obj.put("Message", "Error");
                    response.getWriter().print(obj);
                }
            } else if (op.equals("getProduct")) {
                int pid = Integer.parseInt(request.getParameter("pid"));
                JSONObject result = new JSONObject();

                try {
                	BillApplication bill = new BillApplication();
                	result = bill.getProduct(pid);
                   
                    response.getWriter().print(result);
                } catch (Exception e) {
                    result.put("Message", "Error");
                    response.getWriter().print(result);
                }
            } else if (op.equals("updateProduct")) {
                int productId = Integer.parseInt(request.getParameter("pid"));
                String name = request.getParameter("name");
                float cost = Float.parseFloat(request.getParameter("cost"));
                JSONObject obj = new JSONObject();
                try {
                    BillApplication bill = new BillApplication();
                    bill.updateProduct(productId, name, cost);
                } catch (Exception e) {
                    obj.put("Message", "Error");
                    response.getWriter().print(obj);
                }
            } else if (op.equals("deleteProduct")) {
                int productId = Integer.parseInt(request.getParameter("pid"));
                JSONObject obj = new JSONObject();
                try {
                   BillApplication bill = new BillApplication();
                   bill.deleteProduct(productId);
                } catch (Exception e) {
                    obj.put("Message", "Error");
                    response.getWriter().print(obj);
                }
            } else if (op.equals("getAllProduct")) {
                JSONArray res = new JSONArray();
                try {
                    BillApplication bill = new BillApplication();
                    res = bill.getAllProduct();
                    response.getWriter().print(res);
                } catch (Exception e) {
                    JSONObject obj = new JSONObject();
                    obj.put("Message", "Error");
                    response.getWriter().print(obj);
                }
            } else if (op.equals("addUser")) {
                String name = request.getParameter("name");
                String uname = request.getParameter("uname");
                String pass = request.getParameter("pass");
                

                // Add Product
                JSONObject obj = new JSONObject();
                try {
                	List<FileItem> items = sfu.parseRequest(request);
                    FileItem file = (FileItem) items.get(0);
                	BillApplication bill = new BillApplication();
                	bill.addUser(name, uname, pass, file);

                   
                } catch (Exception e) {
                    obj.put("Message", "Error");
                    response.getWriter().print(obj);
                }
            } else if (op.equals("login")) {
                String uname = request.getParameter("user");
                String pass = request.getParameter("passw");

                // Add Product
                JSONObject result = new JSONObject();
                try {
                	BillApplication bill = new BillApplication();
                	result = bill.login(uname, pass);
                   
                    response.getWriter().print(result);
                } catch (Exception e) {
                    e.printStackTrace();
                   result.put("Message", "Error");
                    response.getWriter().print(result);
                }
            } else if (op.equals("getUserDetail")) {
                String uname = request.getParameter("uname");

                JSONObject obj = new JSONObject();
                try {
                	BillApplication bill = new BillApplication();
                	obj = bill.getUserDetail(uname);
                    response.getWriter().print(obj);
                } catch (Exception e) {
                    e.printStackTrace();
                    obj.put("Message", "Error");
                    response.getWriter().print(obj);
                }
            } else if (op.equals("getProfilePicture")) {
                String uname = request.getParameter("uname");
                
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", password);
                    stmt = conn.createStatement();
                    String query = "select image from image where uname='" + uname + "'";
                    rs = stmt.executeQuery(query);
                    if(rs.next()) {
                        Blob  b = rs.getBlob("image");
                        response.setContentType("image/png;base64;");
                        response.setContentLength( (int) b.length());
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
            }

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

    }

}
