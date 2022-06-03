/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.UserBean;
import ict.bean.UserInfo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author JKc
 */
public class UserDB {
    private String dbUrl="";
    private String dbUsername = "";
    private String dbPassword = "";
    public UserDB(String url,String username, String password){
        this.dbUrl = url;
        this.dbUsername = username;
        this.dbPassword = password;
    }
    public Connection getConnection() throws SQLException, IOException{
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return DriverManager.getConnection(dbUrl,dbUsername, dbPassword);
    }
    public UserInfo isValidUser(String username,String password){
        Connection cnnct = null;
        PreparedStatement stmnt = null;
        UserInfo user = null;
        try{
            cnnct = getConnection();
            String sql = "SELECT * FROM `user` WHERE username = ? AND password = ?;";
            stmnt = cnnct.prepareStatement(sql);
            stmnt.setString(1, username);
            stmnt.setString(2, password);
            ResultSet rs = stmnt.executeQuery();
            if(rs.next()){
                System.out.println(rs.getString("permission"));
                user = new UserInfo(rs.getString("id"),rs.getString("username"),rs.getString("permission"));
            }
        } catch(SQLException ex){
            ex.printStackTrace();
        } catch(IOException ex){
            ex.printStackTrace();
        }
        return user;
    }
    
    //get user details
    public ArrayList<UserInfo> getUser(String userType) throws IOException{
        ArrayList<UserInfo> stuUser = new ArrayList();
        Connection cnnct = null;
        Statement stmnt = null;
        ResultSet rs= null;
        String sql = "SELECT * FROM `user` WHERE permission = '"+userType+"';";
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            
            rs = stmnt.executeQuery(sql);
            
            while(rs.next()){
                UserInfo user = new UserInfo(rs.getString("id"),rs.getString("username"));
                stuUser.add(user);
            }
            cnnct.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return stuUser;
    }
    
    public boolean addUser(UserInfo newUser){
        boolean sucess = false;
        Connection cnnct = null;
        Statement stmnt = null;
        String username = newUser.getUsername();
        String password = newUser.getPassword();
        String permission = newUser.getPermission();
        String sql = "INSERT INTO `user` (`username`, `password`, `permission`) "
                             + "VALUES ('"+username+"','"+password+"','"+permission+"')";
        try{
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            stmnt.execute(sql);
            sucess = true;
            
        }catch(SQLException ex){
            System.out.println("SQL connection error");
        }catch(IOException ex){
            System.out.println("Input error");
        }
        
        return sucess;
    }
    
    public UserBean queryUserByID(String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        UserBean ub = null;
        try {
            //1.  get Connection
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  USER WHERE ID=?";
            //2.  get the prepare Statement
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //3. update the placehoder with id
            pStmnt.setString(1, id);
            ResultSet rs = null;
            //4. execute the query and assign to the result 
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                ub = new UserBean();
                // set the record detail to the customer bean
                ub = new UserBean();
                ub.setId(rs.getInt(1));
                ub.setUsername(rs.getString(2));
                ub.setPassword(rs.getString(3));
                ub.setPermission(rs.getString(4));
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ub;
    }
    
    public ArrayList<UserBean> queryUser() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  USER WHERE PERMISSION = 'stu'";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList<UserBean> list = new ArrayList();

            while (rs.next()) {
                UserBean ub = new UserBean();
                ub.setId(rs.getInt(1));
                ub.setUsername(rs.getString(2));
                ub.setPassword(rs.getString(3));
                ub.setPermission(rs.getString(4));
                list.add(ub);
            }
            return list;
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
        return null;
    }
    
    public ArrayList<UserBean> queryTec() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  USER WHERE PERMISSION = 'tec'";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList<UserBean> list = new ArrayList();

            while (rs.next()) {
                UserBean ub = new UserBean();
                ub.setId(rs.getInt(1));
                ub.setUsername(rs.getString(2));
                ub.setPassword(rs.getString(3));
                ub.setPermission(rs.getString(4));
                list.add(ub);
            }
            return list;
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
        return null;
    }
    
    public ArrayList<UserBean> querySen() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  USER WHERE PERMISSION = 'sen'";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList<UserBean> list = new ArrayList();

            while (rs.next()) {
                UserBean ub = new UserBean();
                ub.setId(rs.getInt(1));
                ub.setUsername(rs.getString(2));
                ub.setPassword(rs.getString(3));
                ub.setPermission(rs.getString(4));
                list.add(ub);
            }
            return list;
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
        return null;
    }
    
    public ArrayList queryUserByName(String name) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM  USER WHERE USERNAME LIKE ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, "%" + name + "%");
            //Statement s = cnnct.createStatement();
            ResultSet rs = pStmnt.executeQuery();

            ArrayList list = new ArrayList();

            while (rs.next()) {
                UserBean ub = new UserBean();
                ub.setId(rs.getInt(1));
                ub.setUsername(rs.getString(2));
                ub.setPassword(rs.getString(3));
                ub.setPermission(rs.getString(4));
                list.add(ub);
            }
            return list;
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
        return null;
    }
    
    
    public boolean delRecord(String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
          int num =0;
        try {
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM USER WHERE ID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);

             num = pStmnt.executeUpdate();

         

        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
         
        
            return (num == 1) ? true : false;   
    }

    public boolean editRecord(UserBean ub) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int num=0;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE USER SET USERNAME=? ,PASSWORD=? ,PERMISSION=? WHERE ID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
           
            pStmnt.setString(1, ub.getUsername());
            pStmnt.setString(2, ub.getPassword());
            pStmnt.setString(3, ub.getPermission()); 
            pStmnt.setInt(4, ub.getId());
            
            //Statement s = cnnct.createStatement();
            num= pStmnt.executeUpdate();
          
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (pStmnt != null) {
                try {
                    pStmnt.close();
                } catch (SQLException e) {
                }
            }
            if (cnnct != null) {
                try {
                    cnnct.close();
                } catch (SQLException sqlEx) {
                }
            }
        }
         return (num == 1) ? true : false;   
    }
    
    public boolean addRecord(int id, String username, String password, String permission) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT  INTO  USER  VALUES  (?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            pStmnt.setString(2, username);
            pStmnt.setString(3, password);
            pStmnt.setString(4, permission);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
}
