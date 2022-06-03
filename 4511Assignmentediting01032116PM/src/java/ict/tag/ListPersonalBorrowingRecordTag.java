/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.tag;

import ict.db.EquipmentDB;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author JKc
 */
public class ListPersonalBorrowingRecordTag extends SimpleTagSupport {
    EquipmentDB db ;
    String userID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    public void setDB() {
        String dbUrl = "jdbc:mysql://localhost:3306/4511_assignment";
        String dbUser = "root";
        String dbPassword = "";
        db = new EquipmentDB(dbUrl, dbUser, dbPassword);
    }
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        setDB();
        try{
            String output = db.listPersonalBorrowingRecord(userID);
            out.println(output);
        } catch (java.io.IOException ex) {
            throw new JspException("Error in ListPersonalBorrowingRecordTag tag", ex);
        }
    }
    
}
