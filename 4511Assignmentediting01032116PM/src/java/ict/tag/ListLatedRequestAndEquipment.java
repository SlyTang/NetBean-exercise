/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.tag;

import ict.bean.BorrowRequestBean;
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
public class ListLatedRequestAndEquipment extends SimpleTagSupport {
    EquipmentDB db ;
    public void setDB() {
        String dbUrl = "jdbc:mysql://localhost:3306/4511_assignment";
        String dbUser = "root";
        String dbPassword = "";
        db = new EquipmentDB(dbUrl, dbUser, dbPassword);
        
    }
    @Override
    public void doTag() throws JspException {
        setDB();
        JspWriter out = getJspContext().getOut();
        ArrayList<String> borrowrequestIDs = db.getLateBorrowRequest();
        
        
        try {
            for(int count = 0 ; count < borrowrequestIDs.size(); count ++){
                BorrowRequestBean currentBean = db.getBorrowRequestDetail(borrowrequestIDs.get(count));
                out.print("<div style='border:1px solid'>id : "+currentBean.getId()+"</div> <div style='border:1px solid'>User ID : "+currentBean.getUserID()+"</div><div style='border:1px solid'> Lend date : "+currentBean.getStartDate()+"</div><div style='border:1px solid'> End date : "+currentBean.getEndDate()+"</div>");
                out.print("<table>\n");
                out.print("<tr><th>Equipment ID</th><th>Equipment Name</th></tr>");
                out.print(db.getLateRequestEquipmentsTable(currentBean.getId()));
                out.print("</table>\n");
            }
        } catch (java.io.IOException ex) {
            throw new JspException("Error in ListLatedRequestAndEquipment tag", ex);
        }
    }
    
}
