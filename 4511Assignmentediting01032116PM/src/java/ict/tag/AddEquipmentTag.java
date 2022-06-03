/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.tag;

import ict.db.EquipmentDB;
import ict.db.UserDB;
import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author JKc
 */
public class AddEquipmentTag extends SimpleTagSupport {

    private String name;
    private String type;
    private String dateLimit;
    private String detail;
    private EquipmentDB db;
    public void setDB() {
        String dbUrl = "jdbc:mysql://localhost:3306/4511_assignment";
        String dbUser = "root";
        String dbPassword = "";
        db = new EquipmentDB(dbUrl, dbUser, dbPassword);
    }


    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws IOException  {
        
        String output= "Fail to add  ";
        JspWriter out = out = getJspContext().getOut();

        
        try {
            setDB();
            PageContext pageContext ;
            pageContext = (PageContext) getJspContext();
            JspFragment f = getJspBody();
            
            
            if(!name.equals("")&&!type.equals("")){
                boolean sucess = db.addEquipment(name, type, dateLimit, detail);
                if(sucess){
                    output = "<br/>Sucess to add new equipment<br/>"+
                                "System will go to list equipment page after 5 secounds";
                    out.print(output);
                    output = "<script type=\"text/javascript\">\n" +
                                    "setTimeout(function(){\n" +
                                    "window.location.href = 'ListEquipmentType.jsp'\n" +
                                    "}, 5000);"+
                                "</script>";
                    out.print(output);
                }
            }
            
        } catch (Exception ex){
            out.print("Ex");
        }
        
        out.print(output);
        
    }
    
    public void setName(String name){
        this.name = name;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setDateLimit(String dateLimit) {
        this.dateLimit = dateLimit;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    
}
