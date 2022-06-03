/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;
import java.io.Serializable;
/**
 *
 * @author JKc
 */
public class UserInfo {
    private String username;
    private String password;
    private String userID;
    private String permission;
    
    public UserInfo(String userID, String username){this.userID = userID; this.username=username;}
    public UserInfo( String userID, String username, String permission){
        this.userID = userID;
        this.permission = permission;
        this.username = username;
    }
    public UserInfo(String userID , String username, String password, String permission){
        this.password = password;
        this.permission = permission;
        this.username = username;
        this.userID = userID;
    }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getUserID() { return userID; }
    public String getPermission() { return permission; }
}
