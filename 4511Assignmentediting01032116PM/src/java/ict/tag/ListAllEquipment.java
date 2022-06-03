/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.tag;

import ict.bean.EquipmentBean;
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
public class ListAllEquipment extends SimpleTagSupport {
    EquipmentDB db;
    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
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
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
            // out.println("<strong>" + attribute_1 + "</strong>");
            // out.println("    <blockquote>");

            ArrayList<EquipmentBean> equipmentsList = db.getEquipment();
            
            for(int count = 0;count < equipmentsList.size();count ++){
                EquipmentBean currentEquipment = equipmentsList.get(count);
                String id = currentEquipment.getId();
                String name = currentEquipment.getName();
                String type = db.getTypeName(currentEquipment.getType());
                int dateLimit = currentEquipment.getRantDateLimit();
                String detail = currentEquipment.getDetail();
                if(detail== null||detail.equals("")){
                    detail = "No detail";
                }
                out.print("<tr id='"+type+"'>\n");
                out.print("<td><input type='checkbox' value='"+id+"' name='equipment'></td>\n");
                
                out.print("<td>"+name+"</td>\n");
                
                out.print("<td>"+type+"</td>\n");
                out.print("<td id='"+id+"'>"+dateLimit+"</td>\n");
                out.print("<td>"+detail+"</td>\n");
                out.print("</tr>\n");
                
            }
            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in ListAllEquipment tag", ex);
        }
    }
    
}
