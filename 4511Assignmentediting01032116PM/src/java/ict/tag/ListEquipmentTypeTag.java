/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.tag;

import ict.bean.EquipmentBean;
import ict.bean.EquipmentTypeBean;
import ict.db.EquipmentDB;
import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author JKc
 */
public class ListEquipmentTypeTag extends SimpleTagSupport {
    EquipmentDB db ;
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
        try {
           ArrayList<EquipmentTypeBean> typeList = db.getType();
           for(int count = 0; count < typeList.size();count ++){
               out.print("<input type='submit' id='equipment' name='"+typeList.get(count).getId()+"' value='"+typeList.get(count).getName()+"'>");
           }
        } catch (java.io.IOException ex) {
            throw new JspException("Error in ListEquipmentTag tag"  , ex);
        }
    }
    
}
