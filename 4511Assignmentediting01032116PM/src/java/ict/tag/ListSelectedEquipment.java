/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.tag;

import ict.bean.EquipmentBean;
import ict.db.EquipmentDB;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author JKc
 */
public class ListSelectedEquipment extends SimpleTagSupport {
    EquipmentDB db = null;
    String [] equipmentsID;

    public Object getEquipmentsID() {
        return equipmentsID;
    }

    public void setEquipmentsID(Object equipmentsID) {
        this.equipmentsID = (String[])equipmentsID;
    }
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
            out.print("<table><tr id=\"top\">\n" +
"                    <td>Equipment name</td>\n" +
"                    <td>Type</td>\n" +
"                    <td>Detail</td><tr>");
            for(int count=0;count <equipmentsID.length;count ++){
                EquipmentBean currentEquipment = db.getEquipment(equipmentsID[count]);
                String name = currentEquipment.getName();
                String id = currentEquipment.getId();
                String type = db.getTypeName(currentEquipment.getType());
                String detail = currentEquipment.getDetail();
                out.print("<tr><td>"+name+"</td>");
                out.print("<td>"+type+"</td>");
                out.print("<td>"+detail+"</td></tr>");
                out.print("<input type='hidden' name='equipmentsSelected' value='"+id+"'>");
            }
            out.print("</table>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in ListSelectedEquipment tag", ex);
        }catch(NullPointerException ex){
            ex.printStackTrace();
        }
    }
    
}
