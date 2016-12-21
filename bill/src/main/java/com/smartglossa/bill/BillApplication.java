package com.smartglossa.bill;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.json.JSONArray;
import org.json.JSONObject;

public class BillApplication {
    Connection conn = null; 
    Statement stmt = null;       
    ResultSet rs = null;
    PreparedStatement ps = null; 

    public BillApplication() throws ClassNotFoundException, SQLException, IOException {
        openConnection();
    }

    public void addProduct(int productId, String pName, float buyPrice,float sellPrice,float quantity,FileItem file)
            throws SQLException, ClassNotFoundException, IOException {
        try {
            String query = "Insert into product value(" + productId + ", '" + pName + "', " + buyPrice + "," +sellPrice+ ","+ quantity +")";
            stmt.execute(query);

            ps = conn.prepareStatement("insert into productImage(pimage,pid) values(?,?)");
            ps.setInt(2, productId);
            // size must be converted to int otherwise it results in error
            ps.setBinaryStream(1, file.getInputStream(), (int) file.getSize());
            ps.executeUpdate();
        } finally {
            closeConnection();
        } 

    }

    public JSONObject getProduct(int pId) throws ClassNotFoundException, SQLException {
        try {
            JSONObject obj = new JSONObject();
            String query = "select * from product where productId = " + pId;
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                obj.put("name", rs.getString(2));
                obj.put("cost", rs.getFloat(3));
            }
            return obj;
        } finally {
            closeConnection();
        }
    }

    public void updateProduct(int productId, String pName, float buyPrice,float sellPrice,float quantity, FileItem file)
            throws ClassNotFoundException, SQLException, IOException {
        try {
            String query = "Update product set pName='" + pName + "',buyPrice= " +buyPrice  + ",sellPrice= "+ sellPrice +",quantity="+ quantity +",where productId= " + productId;
            stmt.execute(query);

            ps = conn.prepareStatement(
                    "Insert into productImage(pimage,pid) values(?,?) ON DUPLICATE KEY update pimage = ?");
            // size must be converted to int otherwise it results in error
            ps.setBinaryStream(1, file.getInputStream(), (int) file.getSize());
            ps.setInt(2, productId);
            ps.setBinaryStream(3, file.getInputStream(), (int) file.getSize());
            ps.executeUpdate();
        } finally {
            closeConnection();
        }
    }

    public void deleteProduct(int productId) throws ClassNotFoundException, SQLException {
        try {
            String query = "Delete from product where productId= " + productId;
            stmt.execute(query);
        } finally {
            closeConnection();
        }
    }

    public JSONArray getAllProduct() throws ClassNotFoundException, SQLException {
        try {
            JSONArray res = new JSONArray();
            String query = "select * from product";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                JSONObject obj = new JSONObject();
				obj.put("productId", rs.getInt("productId"));
                obj.put("pName", rs.getString("pName"));
                obj.put("sellPrice", rs.getFloat("sellPrice"));
                obj.put("buyPrice", rs.getFloat("buyPrice"));
                obj.put("quantity", rs.getFloat("quantity"));
                res.put(obj);
            }
            return res;
        } finally {
            closeConnection();
        }

    }

    public void addUser(String name, String uname, String pass, FileItem file)
            throws ClassNotFoundException, SQLException, IOException, FileUploadException {
        try {
            String query = "Insert into user(name,uname,pass) values('" + name + "', '" + uname + "', '" + pass + "')";
            stmt.execute(query);
            // Store image
            ps = conn.prepareStatement("insert into image(image,uname) values(?,?)");
            ps.setString(2, uname);
            // size must be converted to int otherwise it results in error
            ps.setBinaryStream(1, file.getInputStream(), (int) file.getSize());
            ps.executeUpdate();
        } finally {
            closeConnection();
        }

    }

    public void updateProfile(String uname, FileItem file) throws ClassNotFoundException, SQLException, IOException {
        try {
            ps = conn.prepareStatement("Update image set image = ? where uname = ?");
            ps.setBinaryStream(1, file.getInputStream(), (int) file.getSize());
            ps.setString(2, uname);
            ps.executeUpdate();
        } finally {
            closeConnection();
        }
    }

    public JSONObject login(String uname, String pass) throws ClassNotFoundException, SQLException {
        try {
            JSONObject obj = new JSONObject();
            String query = "select name from user where uname='" + uname + "'AND pass='" + pass + "'";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                obj.put("name", rs.getString(1));
            }
            return obj;
        } finally {
            closeConnection();
        }

    }

    public JSONObject getUserDetail(String uname) throws ClassNotFoundException, SQLException {
        try {
            JSONObject obj = new JSONObject();
            String query = "select name from user where uname='" + uname + "'";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                obj.put("name", rs.getString(1));
            }
            return obj;
        } finally {
            closeConnection();
        }

    }

    public Blob getProfilePicture(String uname) throws ClassNotFoundException, SQLException {
        try {
            String query = "select image from image where uname='" + uname + "'";
            rs = stmt.executeQuery(query);
            Blob b = null;
            if (rs.next()) {
                b = rs.getBlob("image");
            }
            return b;
        } finally {
            closeConnection();
        }
    }

    public Blob getProductImage(int pid) throws ClassNotFoundException, SQLException {
        try {
            String query = "select pimage from productImage where pid=" + pid;
            rs = stmt.executeQuery(query);
            Blob b = null;
            if (rs.next()) {
                b = rs.getBlob("pimage");
            }
            return b;
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
