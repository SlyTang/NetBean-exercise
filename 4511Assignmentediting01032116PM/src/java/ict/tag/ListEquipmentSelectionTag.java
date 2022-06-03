/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.tag;

import ict.bean.EquipmentTypeBean;
import ict.db.EquipmentDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author JKc
 */
public class ListEquipmentSelectionTag extends SimpleTagSupport {
    EquipmentDB db;
    
    public void setDB() {
        String dbUrl = "jdbc:mysql://localhost:3306/4511_assignment";
        String dbUser = "root";
        String dbPassword = "";
        db = new EquipmentDB(dbUrl, dbUser, dbPassword);
    }
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try{
            String htmlCode = getTypeSelection();
            out.print(htmlCode);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    public String getTypeSelection(){
        setDB();
        ArrayList<EquipmentTypeBean> list = db.getType();
        String htmlCode = "";
        htmlCode += ("<select name='type'>");
        htmlCode += ("<option>Select Type</option>");
        EquipmentTypeBean currentType ;
        for(int count = 0; count <list.size();count ++){
            currentType = list.get(count);
            String name = currentType.getName();
            String id = currentType.getId();
            htmlCode += ("<option value='"+id+"'>"+name+"");
            htmlCode += ("</option>");
        }
        htmlCode += ("</select>");
        return htmlCode;
    }
     
}
